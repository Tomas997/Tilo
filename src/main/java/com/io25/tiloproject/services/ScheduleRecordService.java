package com.io25.tiloproject.services;

import com.io25.tiloproject.model.ScheduleRecord;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ScheduleRecordService {


    void deleteById(Long id);

    void saveNewScheduleRecord() throws IOException;

    Optional<List<ScheduleRecord>> findAllRecordsByDate(LocalDate date);
}
