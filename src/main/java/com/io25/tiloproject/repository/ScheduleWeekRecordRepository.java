package com.io25.tiloproject.repository;

import com.io25.tiloproject.model.Coach;
import com.io25.tiloproject.model.ScheduleWeekItem;
import com.io25.tiloproject.model.ScheduleWeekRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ScheduleWeekRecordRepository extends JpaRepository<ScheduleWeekRecord, Long> {


//    @Query("DELETE FROM ScheduleWeekRecord s WHERE s.coach = :coach AND s.dayOfWeek = :dayOfWeek")
//    void deleteByCoachAndDayOfWeek(@Param("coach") String coach, @Param("dayOfWeek") String dayOfWeek);

    @Transactional
    void deleteByCoachIdAndDayOfWeek(Long coachId, Integer dayOfWeek);

//    @Query("SELECT s FROM ScheduleWeekRecord s WHERE s.coach = :coach AND s.dayOfWeek = :dayOfWeek")
//    ScheduleWeekRecord findByCoachAndDayOfWeek1(@Param("coach") String coach, @Param("dayOfWeek") String dayOfWeek);

    ScheduleWeekRecord findByCoachAndDayOfWeek(Coach coach, Integer dayOfWeek);


//    ScheduleWeekRecord findFirstByDayOfWeek(String day);
//
//    ScheduleWeekRecord findByCoach(Coach coach);
}


