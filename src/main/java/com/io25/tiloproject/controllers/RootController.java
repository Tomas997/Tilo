package com.io25.tiloproject.controllers;

import com.io25.tiloproject.model.Role;
import com.io25.tiloproject.services.YogaServiceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
@Slf4j
@RequiredArgsConstructor
public class RootController {

    private static final String SERVICES_VIEW_NAME = "services";
    private static final String SERVICES_ATTRIBUTE_NAME = "services";
    private static final String PRICES_VIEW_NAME = "prices";

    private final YogaServiceService yogaServiceService;

    @GetMapping("services.html")
    public String getService(Model model) {
        loadServices(model);
        return "services/services";
    }
    @GetMapping("/")
    public String getRoot(){
        return "/index";
    }

    @GetMapping("/index.html")
    public String getIndex(){
        return "/index";
    }

    @GetMapping("/cabinet.html")
    public String getCabinet(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/home";
        }
        return "/cabinet/cabinet";
    }

    @GetMapping("prices.html")
    public String getPrice(Model model) {
        loadServices(model);
        return PRICES_VIEW_NAME;
    }

    private String handleHome(Authentication authentication) {
        if (!(authentication != null && authentication.isAuthenticated())) {
            return "redirect:/cabinet.html";
        }
        return "redirect:" + Role.valueOf(((SimpleGrantedAuthority) authentication.getAuthorities().toArray()[0]).getAuthority()).getHomePage();
    }


    @PostMapping("home")
    public String postHome(Authentication authentication) {
        return handleHome(authentication);
    }

    @GetMapping("home")
    public String getHome(Authentication authentication) {
        return handleHome(authentication);
    }


    @GetMapping("instructors.html")
    public String getInstructors() {
        return "redirect:coach/instructors.html";
    }


    private void loadServices(Model model) {
        List<com.io25.tiloproject.model.YogaService> allServices = yogaServiceService.getAllServices();
        model.addAttribute(SERVICES_ATTRIBUTE_NAME, allServices);
    }
}
