package com.io25.tiloproject.repository;

import com.io25.tiloproject.model.Coach;
import com.io25.tiloproject.model.ScheduleRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRecordRepository extends JpaRepository<ScheduleRecord, Long> {

    Optional<ScheduleRecord> findFirstByOrderByDateDesc();

    Optional<List<ScheduleRecord>> findAllByDate(LocalDate date);

    Optional<ScheduleRecord> findByCoachAndDate(Coach coach, LocalDate date);

}

