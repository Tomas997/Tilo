package com.io25.tiloproject.repository;

import com.io25.tiloproject.model.Coach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoachRepository extends JpaRepository<Coach, Long> {
    @Query("SELECT c FROM Coach c ORDER BY c.id DESC LIMIT 1")
    Optional<Coach> findFirst(); // або findFirstByOrderByCreatedAt()


}

