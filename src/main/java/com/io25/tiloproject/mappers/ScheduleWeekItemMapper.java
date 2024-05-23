package com.io25.tiloproject.mappers;

import com.io25.tiloproject.dto.ScheduleWeekRecordDTO;
import com.io25.tiloproject.model.ScheduleWeekItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ScheduleWeekItemMapper {

    ScheduleWeekItemMapper INSTANCE = Mappers.getMapper(ScheduleWeekItemMapper.class);

    ScheduleWeekItem toEntity(ScheduleWeekRecordDTO.ScheduleWeekItemDTO scheduleWeekItemDTO);
    List<ScheduleWeekItem> toEntity(List<ScheduleWeekRecordDTO.ScheduleWeekItemDTO> scheduleWeekItemDTO);
}
