package com.io25.tiloproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.io25.tiloproject.model.YogaService;

@Repository
public interface YogaServicesRepository extends JpaRepository<YogaService, Long> {

}
