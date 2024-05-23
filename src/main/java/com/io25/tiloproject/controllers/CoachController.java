package com.io25.tiloproject.controllers;

import com.io25.tiloproject.config.TiloUserDetails;
import com.io25.tiloproject.model.Coach;
import com.io25.tiloproject.model.ScheduleRecord;
import com.io25.tiloproject.model.YogaService;
import com.io25.tiloproject.repository.CoachRepository;
import com.io25.tiloproject.services.CoachService;
import com.io25.tiloproject.services.YogaServiceService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/coach")
@Controller
@AllArgsConstructor
public class CoachController {
    private CoachRepository repository;
    private CoachService coachService;
    private YogaServiceService yogaServiceService;

    @PreAuthorize("hasRole('ROLE_COACH')")
    @GetMapping("/coach_main")
    public String getCoach(Model model, @RequestParam(value = "trainingDate", defaultValue = "#{T(java.time.LocalDate).now()}") LocalDate trainingDate, Authentication authentication) {
        Long id = ((TiloUserDetails) authentication.getPrincipal()).getUserId();
        Optional<ScheduleRecord> scheduleRecord = coachService.findScheduleRecordByDateAndId(trainingDate, id);

        model.addAttribute("currentDay", trainingDate);

        Map<Long, List<YogaService>> allServices = yogaServiceService.getAllServices().stream()
                .collect(Collectors.groupingBy(YogaService::getId));

        model.addAttribute("services", allServices);

        if (scheduleRecord.isPresent() && !scheduleRecord.get().getSchedule().isEmpty()) {
            model.addAttribute("scheduleRecord", scheduleRecord.get());
        }

        return "coach/Coach_Main";
    }


    @PreAuthorize("hasRole('ROLE_COACH')")
    @GetMapping("")
    public String redirectCoachHome(Authentication authentication) {
        return "redirect:coach/coach_main";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
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
        return "coach/instructors";
    }


}
