package com.io25.tiloproject.services;

import com.io25.tiloproject.dto.YogaServiceDTO;
import com.io25.tiloproject.model.YogaService;

import java.io.IOException;
import java.util.List;

public interface YogaServiceService {

    List<YogaService> getAllServices();

    void deleteById(Long id);

    YogaService saveNewService(YogaServiceDTO yogaServiceDTO) throws IOException;
}