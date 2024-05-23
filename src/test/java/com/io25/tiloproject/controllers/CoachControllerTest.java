package com.io25.tiloproject.controllers;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.io25.tiloproject.config.TiloUserDetails;
import com.io25.tiloproject.model.Coach;
import com.io25.tiloproject.model.ScheduleItem;
import com.io25.tiloproject.model.ScheduleRecord;
import com.io25.tiloproject.model.YogaService;
import com.io25.tiloproject.repository.CoachRepository;
import com.io25.tiloproject.services.CoachService;
import com.io25.tiloproject.services.YogaServiceService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

class CoachControllerTest {

    @Mock
    private CoachRepository repository;

    @Mock
    private CoachService coachService;

    @Mock
    private YogaServiceService yogaServiceService;

    @Mock
    private Authentication authentication;

    @Mock
    private TiloUserDetails userDetails;

    @Mock
    private Model model;

    @Mock
    private HttpServletRequest request;

    @InjectMocks
    private CoachController coachController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCoach() {
        LocalDate trainingDate = LocalDate.now();
        Long userId = 1L;
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(userDetails.getUserId()).thenReturn(userId);

        ScheduleRecord scheduleRecord = mock(ScheduleRecord.class);
        when(coachService.findScheduleRecordByDateAndId(trainingDate, userId)).thenReturn(Optional.of(scheduleRecord));
        when(scheduleRecord.getSchedule()).thenReturn(Collections.<ScheduleItem>singletonList(Mockito.<ScheduleItem>mock(String.valueOf(YogaService.class))));

        List<YogaService> allServices = new ArrayList<>();
        when(yogaServiceService.getAllServices()).thenReturn(allServices);

        String view = coachController.getCoach(model, trainingDate, authentication);

        verify(model).addAttribute("currentDay", trainingDate);
        verify(model).addAttribute("services", allServices.stream().collect(Collectors.groupingBy(YogaService::getId)));
        verify(model).addAttribute("scheduleRecord", scheduleRecord);
        assertThat(view).isEqualTo("coach/Coach_Main");
    }

    @Test
    void testRedirectCoachHome() {
        String view = coachController.redirectCoachHome(authentication);
        assertThat(view).isEqualTo("redirect:coach/coach_main");
    }

    @Test
    void testDeleteById() throws MalformedURLException {
        String id = "1";
        URL url = new URL("http://localhost:8080/previousPage");
        when(request.getHeader("referer")).thenReturn(url.toString());

        String view = coachController.deleteById(id, request);

        verify(coachService).deleteById(Long.parseLong(id));
        assertThat(view).isEqualTo("redirect:" + url.getPath());
    }

    @Test
    void testDeleteById_MalformedURLException() {
        String id = "1";
        when(request.getHeader("referer")).thenReturn("malformedURL");

        String view = coachController.deleteById(id, request);

        verify(coachService).deleteById(Long.parseLong(id));
        assertThat(view).isEqualTo("redirect:/");
    }

    @Test
    void testGetInstructors() {
        List<Coach> allCoaches = new ArrayList<>();
        when(repository.findAll()).thenReturn(allCoaches);

        String view = coachController.getInstructors(model);

        verify(model).addAttribute("coaches", allCoaches);
        assertThat(view).isEqualTo("coach/instructors");
    }
}
