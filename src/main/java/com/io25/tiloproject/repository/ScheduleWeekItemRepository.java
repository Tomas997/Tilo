package com.io25.tiloproject.repository;

import com.io25.tiloproject.model.ScheduleWeekItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleWeekItemRepository extends JpaRepository<ScheduleWeekItem, Long> {

    List<ScheduleWeekItem> findAllByScheduleWeekRecordId(Long id);
}
