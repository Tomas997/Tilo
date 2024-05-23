package com.io25.tiloproject.services;

import com.io25.tiloproject.model.Coach;
import com.io25.tiloproject.model.ScheduleWeekRecord;
import com.io25.tiloproject.repository.ScheduleWeekRecordRepository;
import com.io25.tiloproject.services.impl.ScheduleWeekRecordServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

class ScheduleWeekRecordServiceImplTest {

    @Mock
    private ScheduleWeekRecordRepository scheduleWeekRecordRepository;

    @Mock
    private ScheduleWeekItemService scheduleWeekItemService;

    @InjectMocks
    private ScheduleWeekRecordServiceImpl scheduleWeekRecordService;


    public ScheduleWeekRecordServiceImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllScheduleWeekRecords() {
        when(scheduleWeekRecordRepository.findAll()).thenReturn(Collections.emptyList());

        List<ScheduleWeekRecord> result = scheduleWeekRecordService.getAllScheduleWeekRecords();

        verify(scheduleWeekRecordRepository, times(1)).findAll();
        assert (result.isEmpty());
    }

    @Test
    void testDeleteById() {
        Long id = 1L;
        scheduleWeekRecordService.deleteById(id);
        verify(scheduleWeekRecordRepository, times(1)).deleteById(id);
    }

    @Test
    void testGetScheduleWeekRecord() {
        Coach coach = new Coach();
        Integer day = 1;
        ScheduleWeekRecord scheduleWeekRecord = new ScheduleWeekRecord();
        when(scheduleWeekRecordRepository.findByCoachAndDayOfWeek(coach, day)).thenReturn(scheduleWeekRecord);

        ScheduleWeekRecord result = scheduleWeekRecordService.getScheduleWeekRecord(coach, day);

        verify(scheduleWeekRecordRepository, times(1)).findByCoachAndDayOfWeek(coach, day);
        verify(scheduleWeekItemService, times(1)).findAllByRecordId(scheduleWeekRecord.getId());
        assert (result != null);
    }

//    @Test
//    void testSaveNewScheduleWeekRecord() {
//        ScheduleWeekRecordDTO scheduleWeekRecordDTO = new ScheduleWeekRecordDTO();
//        scheduleWeekRecordDTO.setDay(1);
//        scheduleWeekRecordDTO.setCoach("1"); // Assuming "1" is the coach id
//
//        ScheduleWeekRecordMapper scheduleWeekRecordMapper = mock(ScheduleWeekRecordMapper.class);
//        when(scheduleWeekRecordMapper.dtoToEntity(scheduleWeekRecordDTO)).thenReturn(new ScheduleWeekRecord());
//
//        ScheduleWeekRecordServiceImpl scheduleWeekRecordService = new ScheduleWeekRecordServiceImpl(scheduleWeekRecordRepository, scheduleWeekRecordMapper, scheduleWeekItemService);
//
//        scheduleWeekRecordService.saveNewScheduleWeekRecord(scheduleWeekRecordDTO);
//
//        verify(scheduleWeekRecordRepository, times(1)).deleteByCoachIdAndDayOfWeek(anyLong(), anyInt());
//        verify(scheduleWeekRecordRepository, times(1)).save(any(ScheduleWeekRecord.class));
//    }


}
