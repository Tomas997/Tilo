package com.io25.tiloproject.controllers;

import com.io25.tiloproject.dto.YogaServiceDTO;
import com.io25.tiloproject.model.YogaService;
import com.io25.tiloproject.services.YogaServiceService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@Controller
@RequestMapping("/services")
@Slf4j
@RequiredArgsConstructor
public class ServiceController {

    private static final String SERVICES_ATTRIBUTE_NAME = "services";

    private final YogaServiceService yogaServiceService;

    @DeleteMapping("{id}/delete")
    public String deleteById(@PathVariable String id, HttpServletRequest request) {
        yogaServiceService.deleteById(Long.parseLong(id));
        try {
            URL referer = new URL(request.getHeader("referer"));
            return "redirect:"+referer.getPath();
        } catch (MalformedURLException e){
            return "redirect:/";
        }
    }

    @PostMapping
    public String handleFormUpload(@Valid YogaServiceDTO yogaServiceDTO, BindingResult bindingResult) {
        try {
            yogaServiceService.saveNewService(yogaServiceDTO);
        } catch (Exception ignored){

        }
        return "redirect:/services";
    }

    @GetMapping
    public String upload(Model model) {
        loadServices(model);
        return "services/upload";
    }


    private void loadServices(Model model) {
        List<YogaService> allServices = yogaServiceService.getAllServices();
        model.addAttribute(SERVICES_ATTRIBUTE_NAME, allServices);
    }

}