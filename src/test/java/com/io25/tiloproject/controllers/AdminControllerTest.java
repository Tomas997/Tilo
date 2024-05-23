package com.io25.tiloproject.controllers;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.io25.tiloproject.dto.CoachDTO;
import com.io25.tiloproject.model.*;
import com.io25.tiloproject.repository.ScheduleItemRepository;
import com.io25.tiloproject.services.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

class AdminControllerTest {

    @Mock
    private CoachService coachService;
    @Mock
    private YogaServiceService yogaServiceService;
    @Mock
    private ScheduleWeekRecordService scheduleWeekRecordService;
    @Mock
    private ScheduleWeekItemService scheduleWeekItemService;
    @Mock
    private ScheduleRecordService scheduleRecordService;
    @Mock
    private ScheduleItemRepository scheduleItemRepository;

    @Mock
    private Model model;
    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private AdminController adminController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testHandleFormUpload() throws IOException {
        CoachDTO coachDTO = new CoachDTO();
        String view = adminController.handleFormUpload(coachDTO, bindingResult);

        verify(coachService).saveNewCoach(coachDTO);
        assertThat(view).isEqualTo("redirect:/admin/uploadCoach");
    }

    @Test
    void testUploadCoach() {
        List<Coach> allCoaches = new ArrayList<>();
        when(coachService.getAllCoaches()).thenReturn(allCoaches);

        String view = adminController.uploadCoach(model);

        verify(model).addAttribute("coaches", allCoaches);
        assertThat(view).isEqualTo("admin/uploadCoach");
    }


    @Test
    void testCreateSchedule() throws IOException {
        List<Coach> allCoaches = new ArrayList<>();
        Coach coach = new Coach();
        allCoaches.add(coach);
        when(coachService.getAllCoaches()).thenReturn(allCoaches);
        when(coachService.findById(anyLong())).thenReturn(Optional.of(coach));
        when(scheduleWeekRecordService.getScheduleWeekRecord(any(Coach.class), anyInt())).thenReturn(new ScheduleWeekRecord());
        List<YogaService> allServices = new ArrayList<>();
        when(yogaServiceService.getAllServices()).thenReturn(allServices);

        String view = adminController.createSchedule(1, 1L, model);

        verify(model).addAttribute(eq("coaches"), eq(allCoaches));
        verify(model).addAttribute(eq("services"), eq(allServices));
        verify(model).addAttribute(eq("weekDay"), eq(1));
        verify(model).addAttribute(eq("coachSelected"), eq(1L));
        verify(model).addAttribute(eq("scheduleWeekRecord"), any(ScheduleWeekRecord.class));
        assertThat(view).isEqualTo("admin/createSchedule");
    }


    @Test
    void testRemoveWeekSchedule() {
        String view = adminController.removeWeekSchedule(1L, "1", "1");

        verify(scheduleWeekItemService).deleteById(1L);
        assertThat(view).isEqualTo("redirect:/admin/createSchedule?day=1&coach=1");
    }

    @Test
    void testCreateFullSchedule() throws IOException {
        String view = adminController.createFullSchedule();

        verify(scheduleRecordService).saveNewScheduleRecord();
        assertThat(view).isEqualTo("redirect:/admin/adminMain");
    }

    @Test
    void testAdminMain() {
        List<ScheduleRecord> records = new ArrayList<>();
        when(scheduleRecordService.findAllRecordsByDate(any(LocalDate.class))).thenReturn(Optional.of(records));
        when(yogaServiceService.getAllServices()).thenReturn(new ArrayList<>());

        String view = adminController.adminMain(model, LocalDate.now());

        verify(model).addAttribute("scheduleRecords", records);
        verify(model).addAttribute(eq("services"), anyMap());
        verify(model).addAttribute(eq("currentDay"), any(LocalDate.class));
        assertThat(view).isEqualTo("/admin/Admin_Main");
    }

    @Test
    void testHandleFormUploadDelete() {
        String view = adminController.handleFormUpload(1L);

        verify(scheduleItemRepository).deleteById(1L);
        assertThat(view).isEqualTo("redirect:/admin/adminMain");
    }

    @Test
    void testUpload() {
        List<YogaService> allServices = new ArrayList<>();
        when(yogaServiceService.getAllServices()).thenReturn(allServices);

        String view = adminController.upload(model);

        verify(model).addAttribute("services", allServices);
        assertThat(view).isEqualTo("services/upload");
    }
}
