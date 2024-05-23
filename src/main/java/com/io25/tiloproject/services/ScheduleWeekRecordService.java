package com.io25.tiloproject.services;

import com.io25.tiloproject.dto.ScheduleWeekRecordDTO;
import com.io25.tiloproject.model.Coach;
import com.io25.tiloproject.model.ScheduleWeekRecord;

import java.io.IOException;
import java.util.List;

public interface ScheduleWeekRecordService {

    List<ScheduleWeekRecord> getAllScheduleWeekRecords();

    void deleteById(Long id);

    ScheduleWeekRecord saveNewScheduleWeekRecord(ScheduleWeekRecordDTO scheduleWeekRecordDTO) throws IOException;
    ScheduleWeekRecord getScheduleWeekRecord(Coach coach, Integer day) throws IOException;
}
