package com.io25.tiloproject.controllers;

import com.io25.tiloproject.model.Role;
import com.io25.tiloproject.model.ScheduleRecord;
import com.io25.tiloproject.model.TiloUser;
import com.io25.tiloproject.model.YogaService;
import com.io25.tiloproject.repository.TiloUserRepository;
import com.io25.tiloproject.services.CoachService;
import com.io25.tiloproject.services.ScheduleRecordService;
import com.io25.tiloproject.services.YogaServiceService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequestMapping("/user")
@Controller
@AllArgsConstructor
public class UserController {
    @Autowired
    PasswordEncoder passwordEncoder;
    TiloUserRepository userRepository;
    CoachService coachService;
    YogaServiceService yogaServiceService;
    ScheduleRecordService scheduleRecordService;

    @GetMapping("/orders")
    public String getOrders(Model model,
                            @RequestParam(required = false, defaultValue = "#{T(java.time.LocalDate).now()}") LocalDate trainingDate) {

        List<ScheduleRecord> scheduleRecords = scheduleRecordService.findAllRecordsByDate(trainingDate);
        model.addAttribute("scheduleRecords",scheduleRecords);
        List<YogaService> services = yogaServiceService.getAllServices();
        Map<Long, List<YogaService>> allServices = services.stream().collect(Collectors.groupingBy(YogaService::getId));
        model.addAttribute("services", allServices);
        model.addAttribute("currentDay",trainingDate);
        return "user/User_Main";
    }

    @PostMapping("add/user")
    public String addUser(@RequestParam String name,@RequestParam String phone, @RequestParam String username,
                          @RequestParam String password){
        TiloUser tiloUser = TiloUser.builder()
                .fullName(name)
                .phone(phone)
                .username(username)
                .password(passwordEncoder.encode(password))
                .role(Role.ROLE_USER)
                .build();
        userRepository.save(tiloUser);
        return "redirect:/cabinet.html";
    }
//    private void loadServices(Model model) {
//        List<Coach> coach = coachRepository.findAll();
//            model.addAttribute("coachScheduleRecords", coach);
//    }
}