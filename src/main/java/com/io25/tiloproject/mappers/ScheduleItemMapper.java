package com.io25.tiloproject.mappers;

import com.io25.tiloproject.model.ScheduleItem;
import com.io25.tiloproject.model.ScheduleWeekItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ScheduleItemMapper {
    ScheduleItemMapper INSTANCE = Mappers.getMapper(ScheduleItemMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "quantity",ignore = true)
    ScheduleItem weekToDate(ScheduleWeekItem scheduleWeekItem);
}

