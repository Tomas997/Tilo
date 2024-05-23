package com.io25.tiloproject.controllers;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.io25.tiloproject.config.TiloUserDetails;
import com.io25.tiloproject.dto.ScheduleItemDTO;
import com.io25.tiloproject.model.ScheduleRecord;
import com.io25.tiloproject.model.TiloUser;
import com.io25.tiloproject.model.YogaService;
import com.io25.tiloproject.repository.ScheduleItemRepository;
import com.io25.tiloproject.repository.TiloUserRepository;
import com.io25.tiloproject.services.CoachService;
import com.io25.tiloproject.services.ScheduleItemService;
import com.io25.tiloproject.services.ScheduleRecordService;
import com.io25.tiloproject.services.YogaServiceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

class UserControllerTest {

    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private TiloUserRepository userRepository;
    @Mock
    private ScheduleItemRepository scheduleItemRepository;
    @Mock
    private YogaServiceService yogaServiceService;
    @Mock
    private ScheduleRecordService scheduleRecordService;
    @Mock
    private ScheduleItemService scheduleItemService;
    @Mock
    private Authentication authentication;
    @Mock
    private Model model;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetOrders() {
        TiloUserDetails userDetails = mock(TiloUserDetails.class);
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(userDetails.getUserId()).thenReturn(1L);
        when(userRepository.findTiloUserById(1L)).thenReturn(Optional.of(new TiloUser()));

        List<ScheduleRecord> records = new ArrayList<>();
        when(scheduleRecordService.findAllRecordsByDate(any(LocalDate.class))).thenReturn(Optional.of(records));

        List<YogaService> services = new ArrayList<>();
        when(yogaServiceService.getAllServices()).thenReturn(services);

        String view = userController.getOrders(model, LocalDate.now(), authentication);

        verify(model).addAttribute(eq("currentUser"), any(TiloUser.class));
        verify(model).addAttribute(eq("scheduleRecords"), eq(records));
        verify(model).addAttribute(eq("services"), anyMap());
        verify(model).addAttribute(eq("currentDay"), any(LocalDate.class));
        assertThat(view).isEqualTo("user/User_Main");
    }

    @Test
    void testShowOrder() {
        TiloUserDetails userDetails = mock(TiloUserDetails.class);
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(userDetails.getUserId()).thenReturn(1L);

        Page<ScheduleItemDTO> page = mock(Page.class);
        when(scheduleItemRepository.getAllItemsWithUserId(eq(1L), any(PageRequest.class))).thenReturn(page);

        List<YogaService> services = new ArrayList<>();
        when(yogaServiceService.getAllServices()).thenReturn(services);

        String view = userController.showOrder(authentication, model, 0);

        verify(model).addAttribute("page", page);
        verify(model).addAttribute("services", services.stream().collect(Collectors.groupingBy(YogaService::getId)));

        assertThat(view).isEqualTo("user/User_Order");
    }

    @Test
    void testAddUser() {
        String name = "John";
        String phone = "1234567890";
        String username = "john_doe";
        String password = "password";
        String encodedPassword = "encodedPassword";
        when(passwordEncoder.encode(password)).thenReturn(encodedPassword);

        String view = userController.addUser(name, phone, username, password);

        verify(userRepository).save(any(TiloUser.class));
        assertThat(view).isEqualTo("redirect:/cabinet.html");
    }

    @Test
    void testSubscribe() {
        TiloUserDetails userDetails = mock(TiloUserDetails.class);
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(userDetails.getUserId()).thenReturn(1L);

        LocalDate trainingDate = LocalDate.now();
        LocalTime plannedTime = LocalTime.now().plusHours(1);

        String view = userController.subscribe(1L, authentication, trainingDate, plannedTime);

        verify(scheduleItemService).bookScheduleItem(1L, 1L);
        assertThat(view).isEqualTo("redirect:/user/orders?trainingDate=" + trainingDate);
    }

    @Test
    void testUnSubscribe() {
        TiloUserDetails userDetails = mock(TiloUserDetails.class);
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(userDetails.getUserId()).thenReturn(1L);

        LocalDate trainingDate = LocalDate.now();
        LocalTime plannedTime = LocalTime.now().plusHours(3);

        String view = userController.unSubscribe(1L, authentication, trainingDate, plannedTime);

        verify(scheduleItemService).unBookScheduleItem(1L, 1L);
        assertThat(view).isEqualTo("redirect:/user/orders?trainingDate=" + trainingDate);
    }
}
