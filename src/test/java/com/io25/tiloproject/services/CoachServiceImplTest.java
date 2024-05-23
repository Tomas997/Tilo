package com.io25.tiloproject.services;

import com.io25.tiloproject.model.Coach;
import com.io25.tiloproject.model.ScheduleRecord;
import com.io25.tiloproject.repository.CoachRepository;
import com.io25.tiloproject.repository.ScheduleRecordRepository;
import com.io25.tiloproject.services.impl.CoachServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class CoachServiceImplTest {

    @Mock
    private CoachRepository coachRepository;

    @Mock
    private ScheduleRecordRepository scheduleRecordRepository;

    @InjectMocks
    private CoachServiceImpl coachService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCoaches() {
        List<Coach> coaches = new ArrayList<>();
        coaches.add(new Coach());
        when(coachRepository.findAll()).thenReturn(coaches);

        List<Coach> result = coachService.getAllCoaches();

        assertEquals(1, result.size());
    }



    @Test
    void testFindScheduleRecordByDateAndId() {
        Long id = 1L;
        LocalDate localDate = LocalDate.now();
        Coach coach = new Coach();
        when(coachRepository.findById(id)).thenReturn(Optional.of(coach));
        when(scheduleRecordRepository.findByCoachAndDate(coach, localDate)).thenReturn(Optional.empty());

        Optional<ScheduleRecord> result = coachService.findScheduleRecordByDateAndId(localDate, id);

        assertEquals(Optional.empty(), result);
    }
}
