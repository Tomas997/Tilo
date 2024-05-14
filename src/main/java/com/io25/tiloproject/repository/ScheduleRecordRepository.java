package com.io25.tiloproject.repository;

import com.io25.tiloproject.model.Coach;
import com.io25.tiloproject.model.ScheduleRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRecordRepository extends JpaRepository<ScheduleRecord, Long> {


}
