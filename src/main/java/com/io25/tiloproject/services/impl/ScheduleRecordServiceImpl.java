package com.io25.tiloproject.services.impl;

import com.io25.tiloproject.mappers.ScheduleRecordMapper;
import com.io25.tiloproject.model.ScheduleRecord;
import com.io25.tiloproject.model.ScheduleWeekRecord;
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

    @Override
    public List<ScheduleRecord> getAllScheduleRecords() {
        return scheduleRecordRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        scheduleRecordRepository.findById(id).ifPresent(
                coach -> scheduleRecordRepository.delete(coach)
        );
    }

    @Override
    public void saveNewScheduleRecord() {
        LocalDate lastDate=LocalDate.now().minusWeeks(1);
        Optional<ScheduleRecord> lastRecord = scheduleRecordRepository.findFirstByOrderByDateDesc();
        if (lastRecord.isPresent()){
            lastDate = lastRecord.get().getDate();
        }
        if (lastDate.isAfter(LocalDate.now().plusWeeks(1))) {
            return;
        }

        List<ScheduleWeekRecord> scheduleWeekRecords = scheduleWeekRecordService.getAllScheduleWeekRecords();

        for (ScheduleWeekRecord weekRecord : scheduleWeekRecords) {
            weekRecord.setSchedule(scheduleWeekItemRepository.findAllByScheduleWeekRecordId(weekRecord.getId()));
            ScheduleRecord scheduleRecord = ScheduleRecordMapper.INSTANCE.weekToDate(weekRecord,lastDate);
            scheduleRecordRepository.save(scheduleRecord);
        }

    }


}
