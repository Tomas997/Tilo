package com.io25.tiloproject.services;

import com.io25.tiloproject.dto.CoachDTO;
import com.io25.tiloproject.dto.ScheduleRecordDTO;
import com.io25.tiloproject.model.Coach;
import com.io25.tiloproject.model.ScheduleRecord;

import java.io.IOException;
import java.util.List;

public interface ScheduleRecordService {

    List<ScheduleRecord> getAllScheduleRecords();

    void deleteById(Long id);

    ScheduleRecord saveNewScheduleRecord(ScheduleRecordDTO scheduleRecordDTO) throws IOException;
}
