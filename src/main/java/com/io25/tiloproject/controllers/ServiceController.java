package com.io25.tiloproject.controllers;

import com.io25.tiloproject.dto.YogaServiceDTO;
import com.io25.tiloproject.services.YogaServiceService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

@Controller
@RequestMapping("/services")
@Slf4j
@RequiredArgsConstructor
public class ServiceController {

    private static final String SERVICES_ATTRIBUTE_NAME = "services";

    private final YogaServiceService yogaServiceService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("{id}/delete")
    public String deleteById(@PathVariable String id, HttpServletRequest request) throws IOException {
        yogaServiceService.deleteById(Long.parseLong(id));
        try {
            URL referer = new URL(request.getHeader("referer"));
            return "redirect:"+referer.getPath();
        } catch (MalformedURLException e){
            return "redirect:/";
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public String handleFormUpload(@Valid YogaServiceDTO yogaServiceDTO, BindingResult bindingResult) {
        try {
            yogaServiceService.saveNewService(yogaServiceDTO);
        } catch (Exception ignored){

        }
        return "redirect:/admin/services";
    }






}