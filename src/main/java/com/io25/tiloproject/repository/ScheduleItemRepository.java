package com.io25.tiloproject.repository;

import com.io25.tiloproject.model.ScheduleItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleItemRepository extends JpaRepository<ScheduleItem,Long> {
}
