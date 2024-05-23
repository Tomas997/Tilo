package com.io25.tiloproject.services.impl;

import com.io25.tiloproject.mappers.ScheduleItemMapper;
import com.io25.tiloproject.mappers.ScheduleRecordMapper;
import com.io25.tiloproject.model.ScheduleItem;
import com.io25.tiloproject.model.ScheduleRecord;
import com.io25.tiloproject.model.ScheduleWeekItem;
import com.io25.tiloproject.model.ScheduleWeekRecord;
import com.io25.tiloproject.repository.ScheduleItemRepository;
import com.io25.tiloproject.repository.ScheduleRecordRepository;
import com.io25.tiloproject.repository.ScheduleWeekItemRepository;
import com.io25.tiloproject.services.ScheduleRecordService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ScheduleRecordServiceImpl implements ScheduleRecordService {
    private ScheduleRecordRepository scheduleRecordRepository;
    private ScheduleWeekRecordServiceImpl scheduleWeekRecordService;
    private ScheduleWeekItemRepository scheduleWeekItemRepository;
    private ScheduleItemRepository scheduleItemRepository;


    @Override
    public void deleteById(Long id) {
        scheduleRecordRepository.findById(id).ifPresent(
                coach -> scheduleRecordRepository.delete(coach)
        );
    }

    @Override
    public void saveNewScheduleRecord() {
        LocalDate lastDate = scheduleRecordRepository.findFirstByOrderByDateDesc()
                .map(ScheduleRecord::getDate)
                .orElse(LocalDate.now().minusWeeks(1));

        if (lastDate.isAfter(LocalDate.now().plusWeeks(1))) {
            return;
        }

        List<ScheduleWeekRecord> scheduleWeekRecords = scheduleWeekRecordService.getAllScheduleWeekRecords();

        for (ScheduleWeekRecord weekRecord : scheduleWeekRecords) {
            ScheduleRecord scheduleRecord = ScheduleRecordMapper.INSTANCE.weekToDate(weekRecord, lastDate);
            scheduleRecordRepository.save(scheduleRecord);

            List<ScheduleWeekItem> allScheduleWeekItem = scheduleWeekItemRepository.findAllByScheduleWeekRecordId(weekRecord.getId());
            List<ScheduleItem> scheduleItems = ScheduleItemMapper.INSTANCE.weekToDate(allScheduleWeekItem);

            scheduleItems.forEach(item -> item.setScheduleRecord(scheduleRecord));
            scheduleItemRepository.saveAll(scheduleItems);
        }
    }


    @Override
    public Optional<List<ScheduleRecord>> findAllRecordsByDate(LocalDate date) {
        return scheduleRecordRepository.findAllByDate(date);
    }


}
