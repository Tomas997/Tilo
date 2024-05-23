package com.io25.tiloproject.services;

import com.io25.tiloproject.dto.CoachDTO;
import com.io25.tiloproject.model.Coach;
import com.io25.tiloproject.model.ScheduleRecord;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CoachService {
    List<Coach> getAllCoaches();

    void deleteById(Long id);

    Coach saveNewCoach(CoachDTO coachDTO) throws IOException;

    Optional<Coach> findById(Long id);

    Optional<ScheduleRecord> findScheduleRecordByDateAndId(LocalDate localDate, Long id);
}
