package com.io25.tiloproject.repository;

import com.io25.tiloproject.dto.ScheduleItemDTO;
import com.io25.tiloproject.model.ScheduleItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleItemRepository extends JpaRepository<ScheduleItem,Long> {


    @Query("SELECT " +
            "new com.io25.tiloproject.dto.ScheduleItemDTO(r.date,s.time,s.service,r.coach.fullName) " +
            "FROM TiloUser u JOIN u.scheduleItems s JOIN s.scheduleRecord r WHERE u.id = :userId order by r.date,s.time asc")
    Page<ScheduleItemDTO> getAllItemsWithUserId(@Param("userId") Long userId, Pageable pageable);


}
