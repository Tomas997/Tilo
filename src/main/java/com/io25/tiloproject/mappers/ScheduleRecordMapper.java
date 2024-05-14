package com.io25.tiloproject.mappers;

import com.io25.tiloproject.dto.ScheduleRecordDTO;
import com.io25.tiloproject.dto.YogaServiceDTO;
import com.io25.tiloproject.model.ScheduleRecord;
import com.io25.tiloproject.model.YogaService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ScheduleRecordMapper {
    ScheduleRecordMapper INSTANCE = Mappers.getMapper(ScheduleRecordMapper.class);

    //    @Mapping(target = "name", source = "name", qualifiedByName = "toUpperCase")

    ScheduleRecord dtoToEntity(ScheduleRecordDTO dto);
}
