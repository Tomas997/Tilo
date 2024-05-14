package com.io25.tiloproject.controllers;

import com.io25.tiloproject.model.Coach;
import com.io25.tiloproject.repository.CoachRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@RequestMapping("/user")
@Controller
@AllArgsConstructor
public class UserController {
    CoachRepository coachRepository;

    @GetMapping("/orders")
    public String getOrders(Model model){
        List<Coach> coach = coachRepository.findAll();
        model.addAttribute("coaches", coach);
//        model.addAttribute("coachScheduleRecords", coach.));
        return "user/User_Main";
    }

//    private void loadServices(Model model) {
//        List<Coach> coach = coachRepository.findAll();
//            model.addAttribute("coachScheduleRecords", coach);
//    }
}