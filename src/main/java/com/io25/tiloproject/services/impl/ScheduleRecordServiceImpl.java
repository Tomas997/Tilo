package com.io25.tiloproject.services.impl;

import com.io25.tiloproject.dto.ScheduleRecordDTO;
import com.io25.tiloproject.mappers.CoachMapper;
import com.io25.tiloproject.mappers.ScheduleRecordMapper;
import com.io25.tiloproject.model.Coach;
import com.io25.tiloproject.model.ScheduleRecord;
import com.io25.tiloproject.repository.ScheduleRecordRepository;
import com.io25.tiloproject.services.ScheduleRecordService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class ScheduleRecordServiceImpl implements ScheduleRecordService {
    private ScheduleRecordRepository scheduleRecordRepository;
    @Override
    public List<ScheduleRecord> getAllScheduleRecords() {
        return scheduleRecordRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        scheduleRecordRepository.findById(id).ifPresent(
                coach -> {
                    scheduleRecordRepository.delete(coach);
                }
        );
    }

    @Override
    public ScheduleRecord saveNewScheduleRecord(ScheduleRecordDTO scheduleRecordDTO) throws IOException {
        ScheduleRecord scheduleRecord = ScheduleRecordMapper.INSTANCE.dtoToEntity(scheduleRecordDTO);
        scheduleRecordRepository.save(scheduleRecord);
        return scheduleRecord;
    }
}
