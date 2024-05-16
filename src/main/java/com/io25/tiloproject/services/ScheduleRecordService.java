package com.io25.tiloproject.services;

import com.io25.tiloproject.model.ScheduleRecord;

import java.io.IOException;
import java.util.List;

public interface ScheduleRecordService {

    List<ScheduleRecord> getAllScheduleRecords();

    void deleteById(Long id);

    void saveNewScheduleRecord() throws IOException;
}
