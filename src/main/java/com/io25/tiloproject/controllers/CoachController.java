package com.io25.tiloproject.controllers;

import com.io25.tiloproject.model.Coach;
import com.io25.tiloproject.repository.CoachRepository;
import com.io25.tiloproject.services.CoachService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

@RequestMapping("/coach")
@Controller
@AllArgsConstructor
public class CoachController {
    private CoachRepository repository;
    private CoachService coachService;

    @GetMapping("/{id}")
    public String getCoach(@PathVariable Long id, Model model) {
        loadServices(model, id);
        return "coach/Coach_Main";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteById(@PathVariable String id, HttpServletRequest request) {
        coachService.deleteById(Long.parseLong(id));
        try {
            URL referer = new URL(request.getHeader("referer"));
            return "redirect:" + referer.getPath();
        } catch (MalformedURLException e) {
            return "redirect:/";
        }
    }

    @GetMapping("/instructors.html")
    public String getInstructors(Model model) {
        List<Coach> allCoaches = repository.findAll();
        model.addAttribute("coaches", allCoaches);
        return "coach/instructors.html";
    }

    private void loadServices(Model model, Long id) {
        Optional<Coach> coach = repository.findById(id);
        coach.ifPresent(coach1 -> {
            model.addAttribute("coachScheduleRecords", coach1.getScheduleRecords());
        });

    }
}
