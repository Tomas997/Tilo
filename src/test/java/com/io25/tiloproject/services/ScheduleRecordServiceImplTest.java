package com.io25.tiloproject.services;

import com.io25.tiloproject.model.ScheduleRecord;
import com.io25.tiloproject.repository.ScheduleRecordRepository;
import com.io25.tiloproject.services.impl.ScheduleRecordServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ScheduleRecordServiceImplTest {

    @Mock
    private ScheduleRecordRepository scheduleRecordRepository;


    @InjectMocks
    private ScheduleRecordServiceImpl scheduleRecordService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeleteById() {
        Long id = 1L;
        when(scheduleRecordRepository.findById(id)).thenReturn(Optional.of(new ScheduleRecord()));

        scheduleRecordService.deleteById(id);

        verify(scheduleRecordRepository, times(1)).delete(any(ScheduleRecord.class));
    }

    @Test
    void testFindAllRecordsByDate() {
        LocalDate date = LocalDate.now();
        when(scheduleRecordRepository.findAllByDate(date)).thenReturn(Optional.ofNullable(null));

        Optional<List<ScheduleRecord>> result = scheduleRecordService.findAllRecordsByDate(date);

        assertEquals(Optional.ofNullable(null), result);
    }

    // Add more tests for other methods as needed
}
