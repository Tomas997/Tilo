package com.io25.tiloproject.controllers;

import com.io25.tiloproject.dto.ScheduleWeekRecordDTO;
import com.io25.tiloproject.model.ScheduleWeekRecord;
import com.io25.tiloproject.services.ScheduleRecordService;
import com.io25.tiloproject.services.ScheduleWeekItemService;
import com.io25.tiloproject.services.ScheduleWeekRecordService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@Slf4j
@AllArgsConstructor
public class ScheduleController {
//    private ScheduleRecordRepository scheduleRecordRepository;
//    private ScheduleRecordService scheduleRecordService;
    private ScheduleWeekRecordService scheduleWeekRecordService;
    private ScheduleWeekItemService scheduleWeekItemService;

//    @PostMapping
//    public String handleFormUpload(@Valid ScheduleRecordDTO scheduleRecordDTO, BindingResult bindingResult) {
//        try {
//            scheduleRecordService.saveNewScheduleRecord(scheduleRecordDTO);
//        } catch (Exception ignored){
//
//        }
//        return "redirect:/schedule";
//    }
//    @PostMapping("/schedule/add")
//    public String handleFormUpload(@RequestBody ScheduleWeekRecordDTO txt) {
//        try {
//            System.out.println(txt);
//        } catch (Exception ignored){
//
//        }
//        return "redirect:/schedule";
//    }

    @PostMapping("/schedule/add")
    public String handleFormUpload(@Valid @RequestBody ScheduleWeekRecordDTO scheduleWeekRecordDTO, BindingResult bindingResult) {
        try {
            ScheduleWeekRecord scheduleWeekRecord = scheduleWeekRecordService.saveNewScheduleWeekRecord(scheduleWeekRecordDTO);
            scheduleWeekItemService.saveScheduleItems(scheduleWeekRecordDTO.getItems(),scheduleWeekRecord);

        } catch (Exception ignored) {
            System.out.println(ignored);
        }
        return "/admin/createSchedule?day="+scheduleWeekRecordDTO.getDay()+"&coach="+scheduleWeekRecordDTO.getCoach();
    }
}
