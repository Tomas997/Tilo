package com.io25.tiloproject.services;

import com.io25.tiloproject.dto.ScheduleWeekRecordDTO;
import com.io25.tiloproject.model.ScheduleWeekItem;
import com.io25.tiloproject.model.ScheduleWeekRecord;

import java.util.List;

public interface ScheduleWeekItemService {
    void deleteById(Long id);

    List<ScheduleWeekItem> findAllByRecordId(Long id);

    void saveScheduleItems(List<ScheduleWeekRecordDTO.ScheduleWeekItemDTO> items, ScheduleWeekRecord scheduleWeekRecord);
}
