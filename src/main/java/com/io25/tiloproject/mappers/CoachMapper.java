package com.io25.tiloproject.mappers;

import com.io25.tiloproject.dto.CoachDTO;
import com.io25.tiloproject.dto.YogaServiceDTO;
import com.io25.tiloproject.model.Coach;
import com.io25.tiloproject.model.YogaService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.web.multipart.MultipartFile;

@Mapper
public interface CoachMapper {
    CoachMapper INSTANCE = Mappers.getMapper(CoachMapper.class);

    @Mapping(target = "imgName", expression = "java(convertToImgName(dto.getImg()))")
    @Mapping(target = "phone", source = "phoneNumber")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "username", source = "username")

    Coach dtoToEntity(CoachDTO dto);

    default String convertToImgName(MultipartFile img) {
        return (img != null) ? img.getOriginalFilename() : null;
    }
}


