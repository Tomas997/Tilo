package com.io25.tiloproject.services.impl;

import com.io25.tiloproject.dto.ScheduleWeekRecordDTO;
import com.io25.tiloproject.mappers.ScheduleWeekRecordMapper;
import com.io25.tiloproject.model.Coach;
import com.io25.tiloproject.model.ScheduleWeekRecord;
import com.io25.tiloproject.repository.ScheduleWeekRecordRepository;
import com.io25.tiloproject.services.ScheduleWeekRecordService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class ScheduleWeekRecordServiceImpl implements ScheduleWeekRecordService {
    private ScheduleWeekRecordRepository scheduleWeekRecordRepository;

    private ScheduleWeekRecordMapper scheduleWeekRecordMapper;

    @Override
    public List<ScheduleWeekRecord> getAllScheduleWeekRecords() {
        return scheduleWeekRecordRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        scheduleWeekRecordRepository.deleteById(id);
    }

    @Override
    public ScheduleWeekRecord saveNewScheduleWeekRecord(ScheduleWeekRecordDTO scheduleWeekRecordDTO) {
        ScheduleWeekRecord scheduleRecord = scheduleWeekRecordMapper.dtoToEntity(scheduleWeekRecordDTO);
        scheduleWeekRecordRepository.deleteByCoachIdAndDayOfWeek(scheduleRecord.getCoach().getId(), scheduleRecord.getDayOfWeek());
        scheduleWeekRecordRepository.save(scheduleRecord);
        return scheduleRecord;

    }

    @Override
    public ScheduleWeekRecord getScheduleWeekRecord(Coach coach, Integer day) {
        return scheduleWeekRecordRepository.findByCoachAndDayOfWeek(coach,day);
    }

//    @Override
//    public ScheduleWeekRecord getScheduleWeekRecord(Coach coach, String day) {
//        if (coach == null){
//            return scheduleWeekRecordRepository.findFirstByDayOfWeek(day);
//        }
//        return scheduleWeekRecordRepository.findByCoachAndDayOfWeek(coach, day);
//    }
}



