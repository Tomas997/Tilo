package com.io25.tiloproject.controllers;

import com.io25.tiloproject.dto.CoachDTO;
import com.io25.tiloproject.model.Coach;
import com.io25.tiloproject.model.ScheduleWeekRecord;
import com.io25.tiloproject.model.YogaService;
import com.io25.tiloproject.services.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin")
@Slf4j
@AllArgsConstructor
public class AdminController {
    private static final String SERVICES_ATTRIBUTE_NAME = "coaches";
    private CoachService coachService;
    private YogaServiceService yogaServiceService;
    private ScheduleWeekRecordService scheduleWeekRecordService;
    private ScheduleWeekItemService scheduleWeekItemService;

    @PostMapping()
    public String handleFormUpload(@Valid CoachDTO coachDTO, BindingResult bindingResult) {
        try {
            coachService.saveNewCoach(coachDTO);
        } catch (Exception ignored) {
            System.out.println(ignored);
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
        if (scheduleWeekRecord != null) {
            scheduleWeekRecord.setSchedule(scheduleWeekItemService.findAllByRecordId(scheduleWeekRecord.getId()));
        }
        model.addAttribute("scheduleWeekRecord", scheduleWeekRecord);
        return "admin/createSchedule";
    }

    @DeleteMapping("/schedule/delete")
    public String removeWeekSchedule(@RequestParam Long id, String day, String coach) {
        scheduleWeekItemService.deleteById(id);
        return "redirect:/admin/createSchedule?day=" + day + "&coach=" + coach;
    }

    @GetMapping("full_schedule/add")
    public String createFullSchedule(){

        return "admin/fullSchedule";
    }


//    @GetMapping("/schedule/load")
//    public String loadWeekRecords( String day, String coach, Model model) throws IOException {
//        List<Coach> allCoaches = coachService.getAllCoaches();
//        model.addAttribute("coaches", allCoaches);
//        List<YogaService> allServices = yogaServiceService.getAllServices();
//        model.addAttribute("services", allServices);
////        ScheduleWeekRecord scheduleWeekRecord = scheduleWeekRecordService.getScheduleWeekRecord(scheduleWeekRecordDTO);
////        model.addAttribute("scheduleWeekRecord",scheduleWeekRecord);
//        return "admin/createSchedule";
//    }


//    @PostMapping("/schedule/add")
//    public String handleFormUpload(@RequestBody ScheduleWeekRecordDTO t) {
//        try {
//            System.out.println(t);
//        } catch (Exception ignored){
//
//        }
//        return "redirect:/admin/createSchedule";
//    }

//    private void loadServices(Model model) {
//        List<Coach> allCoaches = coachService.getAllCoaches();
//        model.addAttribute(SERVICES_ATTRIBUTE_NAME, allCoaches);
//    }
}
