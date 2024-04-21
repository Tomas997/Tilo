package com.io25.tiloproject.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.io25.tiloproject.services.YogaServiceService;

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
    public String getService(Model model){
        loadServices(model);
        return SERVICES_VIEW_NAME;
    }

    @GetMapping("prices.html")
    public String getPrice(Model model){
        loadServices(model);
        return PRICES_VIEW_NAME;
    }

    private void loadServices(Model model) {
        List<com.io25.tiloproject.model.YogaService> allServices = yogaServiceService.getAllServices();
        model.addAttribute(SERVICES_ATTRIBUTE_NAME, allServices);
    }
}
