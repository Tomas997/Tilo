package com.io25.tiloproject.mappers;

import com.io25.tiloproject.model.ScheduleItem;
import com.io25.tiloproject.model.ScheduleWeekItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface ScheduleItemMapper {
    ScheduleItemMapper INSTANCE = Mappers.getMapper(ScheduleItemMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "quantity",constant = "0")
    ScheduleItem weekToDate(ScheduleWeekItem scheduleWeekItem);


    ArrayList<ScheduleItem> weekToDate(List<ScheduleWeekItem> allScheduleWeekItem);
}

