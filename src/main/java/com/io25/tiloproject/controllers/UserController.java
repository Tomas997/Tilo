package com.io25.tiloproject.controllers;

import com.io25.tiloproject.config.TiloUserDetails;
import com.io25.tiloproject.dto.ScheduleItemDTO;
import com.io25.tiloproject.model.Role;
import com.io25.tiloproject.model.ScheduleRecord;
import com.io25.tiloproject.model.TiloUser;
import com.io25.tiloproject.model.YogaService;
import com.io25.tiloproject.repository.ScheduleItemRepository;
import com.io25.tiloproject.repository.TiloUserRepository;
import com.io25.tiloproject.services.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@RequestMapping("/user")
@Controller
@AllArgsConstructor
public class UserController {
    private static final int PAGE_SIZE = 10;
    PasswordEncoder passwordEncoder;
    TiloUserRepository userRepository;
    ScheduleItemRepository scheduleItemRepository;
    CoachService coachService;
    YogaServiceService yogaServiceService;
    ScheduleRecordService scheduleRecordService;
    ScheduleItemService scheduleItemService;
    TiloUsersDetailsService tiloUsersDetailsService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/orders")
    public String getOrders(Model model,
                            @RequestParam(required = false, defaultValue = "#{T(java.time.LocalDate).now()}") LocalDate trainingDate, Authentication authentication) {

        Long id = ((TiloUserDetails) authentication.getPrincipal()).getUserId();
        TiloUser user = userRepository.findTiloUserById(id).orElse(null);
        model.addAttribute("currentUser", user);

        Optional<List<ScheduleRecord>> scheduleRecords = scheduleRecordService.findAllRecordsByDate(trainingDate);
        scheduleRecords.ifPresent(records -> model.addAttribute("scheduleRecords", records));

        List<YogaService> services = yogaServiceService.getAllServices();
        Map<Long, List<YogaService>> allServices = services.stream().collect(Collectors.groupingBy(YogaService::getId));
        model.addAttribute("services", allServices);
        model.addAttribute("currentDay", trainingDate);
        return "user/User_Main";
    }

//    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("User_Order.html")
    public String showOrder(Authentication authentication, Model model,
                            @RequestParam(defaultValue = "0" ) Integer page) {


        Long userId = ((TiloUserDetails) authentication.getPrincipal()).getUserId();

        Page<ScheduleItemDTO> allItemsWithUserId = scheduleItemRepository.getAllItemsWithUserId(userId, PageRequest.of(page, PAGE_SIZE));

        model.addAttribute("page", allItemsWithUserId);

        List<YogaService> services = yogaServiceService.getAllServices();
        Map<Long, List<YogaService>> allServices = services.stream().collect(Collectors.groupingBy(YogaService::getId));
        model.addAttribute("services", allServices);

        return "user/User_Order";
    }


    @PostMapping("add/user")
    public String addUser(@RequestParam String name, @RequestParam String phone, @RequestParam String username,
                          @RequestParam String password) {
        TiloUser tiloUser = new TiloUser(null,name,phone,username,passwordEncoder.encode(password),Role.ROLE_USER,new ArrayList<>());
        userRepository.save(tiloUser);
        return "redirect:/cabinet.html";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/subscribe")
    public String subscribe(@RequestParam Long scheduleItemId, Authentication authentication,
                            @RequestParam(required = false, defaultValue = "#{T(java.time.LocalDate).now()}") LocalDate trainingDate,
                            @RequestParam(required = false) LocalTime plannedTime) {

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime trainingDateTime = LocalDateTime.of(trainingDate, plannedTime);
        if (!(!trainingDateTime.isAfter(now) && trainingDateTime.isBefore(now))) {
            Long id = ((TiloUserDetails) authentication.getPrincipal()).getUserId();
            scheduleItemService.bookScheduleItem(id, scheduleItemId);
        }
        return "redirect:/user/orders?" + "trainingDate=" + trainingDate;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/unsubscribe")
    public String unSubscribe(@RequestParam Long scheduleItemId, Authentication authentication,
                              @RequestParam(required = false, defaultValue = "#{T(java.time.LocalDate).now()}") LocalDate trainingDate,
                              @RequestParam(required = false) LocalTime plannedTime) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime trainingDateTime = LocalDateTime.of(trainingDate, plannedTime);
        if (!(!trainingDateTime.isAfter(now) && trainingDateTime.minusHours(2).isBefore(now))) {
            Long id = ((TiloUserDetails) authentication.getPrincipal()).getUserId();
            scheduleItemService.unBookScheduleItem(id, scheduleItemId);
        }
        return "redirect:/user/orders?" + "trainingDate=" + trainingDate;
    }

}