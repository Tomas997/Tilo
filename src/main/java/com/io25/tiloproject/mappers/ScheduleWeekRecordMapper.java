package com.io25.tiloproject.mappers;

import com.io25.tiloproject.dto.ScheduleWeekRecordDTO;
import com.io25.tiloproject.model.ScheduleWeekRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", uses = CoachQualifier.class)
public interface ScheduleWeekRecordMapper {

    @Mapping(target = "dayOfWeek", source = "day")
    @Mapping(target = "coach", source = "coach", qualifiedByName = "stringToCoach")
    ScheduleWeekRecord dtoToEntity(ScheduleWeekRecordDTO dto);

    //ScheduleWeekItem dtoToEntity(ScheduleWeekRecordDTO.ScheduleWeekItemDTO scheduleWeekItemDTO);


}
