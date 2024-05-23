package com.io25.tiloproject.controllers;

import com.io25.tiloproject.dto.CoachDTO;
import com.io25.tiloproject.model.Coach;
import com.io25.tiloproject.model.ScheduleRecord;
import com.io25.tiloproject.model.ScheduleWeekRecord;
import com.io25.tiloproject.model.YogaService;
import com.io25.tiloproject.repository.ScheduleItemRepository;
import com.io25.tiloproject.services.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
@Slf4j
@AllArgsConstructor
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {
    private static final String SERVICES_ATTRIBUTE_NAME = "coaches";
    private CoachService coachService;
    private YogaServiceService yogaServiceService;
    private ScheduleWeekRecordService scheduleWeekRecordService;
    private ScheduleWeekItemService scheduleWeekItemService;
    private ScheduleRecordService scheduleRecordService;
    private ScheduleItemRepository scheduleItemRepository;

    @PostMapping()
    public String handleFormUpload(@Valid CoachDTO coachDTO, BindingResult bindingResult) {
        try {
            coachService.saveNewCoach(coachDTO);
        } catch (Exception ignored) {
        }
        return "redirect:/admin/uploadCoach";
    }

    @GetMapping("/uploadCoach")
    public String uploadCoach(Model model) {
        List<Coach> allCoaches = coachService.getAllCoaches();
        model.addAttribute(SERVICES_ATTRIBUTE_NAME, allCoaches);
        return "admin/uploadCoach";
    }

    @GetMapping("/createSchedule")
    public String createSchedule(@RequestParam(required = false, defaultValue = "1") Integer day,
                                 @RequestParam(required = false, defaultValue = "1") Long coach,
                                 Model model) throws IOException {
        List<Coach> allCoaches = coachService.getAllCoaches();
        if (allCoaches.isEmpty()) {
            return "redirect:/admin/uploadCoach";
        }
        model.addAttribute(SERVICES_ATTRIBUTE_NAME, allCoaches);
        List<YogaService> allServices = yogaServiceService.getAllServices();
        model.addAttribute("services", allServices);
        model.addAttribute("weekDay", day);
        model.addAttribute("coachSelected", coach);
        Coach currentCoach = coachService.findById(coach).orElse(allCoaches.get(0));
        ScheduleWeekRecord scheduleWeekRecord = scheduleWeekRecordService.getScheduleWeekRecord(currentCoach, day);
        model.addAttribute("scheduleWeekRecord", scheduleWeekRecord);
        return "admin/createSchedule";
    }

    @DeleteMapping("/schedule/delete")
    public String removeWeekSchedule(@RequestParam Long id, String day, String coach) {
        scheduleWeekItemService.deleteById(id);
        return "redirect:/admin/createSchedule?day=" + day + "&coach=" + coach;
    }

    @PostMapping("full_schedule/add")
    public String createFullSchedule() throws IOException {
        scheduleRecordService.saveNewScheduleRecord();
        return "redirect:/admin/adminMain";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/adminMain")
    public String adminMain(Model model,
                            @RequestParam(required = false, defaultValue = "#{T(java.time.LocalDate).now()}") LocalDate trainingDate) {
        Optional<List<ScheduleRecord>> scheduleRecords = scheduleRecordService.findAllRecordsByDate(trainingDate);
        scheduleRecords.ifPresent(records -> model.addAttribute("scheduleRecords", records));

        List<YogaService> services = yogaServiceService.getAllServices();
        Map<Long, List<YogaService>> allServices = services.stream()
                .collect(Collectors.groupingBy(YogaService::getId));
        model.addAttribute("services", allServices);
        model.addAttribute("currentDay", trainingDate);
        return "/admin/Admin_Main";
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/service/del/{id}")
    public String handleFormUpload(@PathVariable Long id) {
        scheduleItemRepository.deleteById(id);
        return "redirect:/admin/adminMain";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/services")
    public String upload(Model model) {
        List<YogaService> allServices = yogaServiceService.getAllServices();
        model.addAttribute("services", allServices);
        return "services/upload";
    }

}
