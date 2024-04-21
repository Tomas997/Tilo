package com.io25.tiloproject.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.web.multipart.MultipartFile;
import com.io25.tiloproject.dto.YogaServiceDTO;
import com.io25.tiloproject.model.YogaService;

@Mapper
public interface YogaServiceMapper {
    YogaServiceMapper INSTANCE = Mappers.getMapper(YogaServiceMapper.class);

//    @Mapping(target = "name", source = "name", qualifiedByName = "toUpperCase")
    @Mapping(target = "imgName", source = "img")
    YogaService dtoToEntity(YogaServiceDTO dto);

    default String convertToImgName(MultipartFile img) {
        return (img != null) ? img.getOriginalFilename() : null;
    }

//    @Named("toUpperCase")
//    default String toUpperCase(String name) {
//        return name.toUpperCase();
//    }
}
