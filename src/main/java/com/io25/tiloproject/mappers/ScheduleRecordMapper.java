package com.io25.tiloproject.mappers;

import com.io25.tiloproject.model.ScheduleRecord;
import com.io25.tiloproject.model.ScheduleWeekRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.time.DayOfWeek;
import java.time.LocalDate;



@Mapper
public interface ScheduleRecordMapper {
    ScheduleRecordMapper INSTANCE = Mappers.getMapper(ScheduleRecordMapper.class);

    @Mapping(target = "date", expression = "java(convertToDate(scheduleWeekRecord.getDayOfWeek(), baseDate))")
    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "schedule", expression = "java(new ArrayList<>())")
    ScheduleRecord weekToDate(ScheduleWeekRecord scheduleWeekRecord, LocalDate baseDate);
    default LocalDate convertToDate(Integer dayOfWeek, LocalDate baseDate) {
        if (dayOfWeek == null || baseDate == null) {
            return null;
        }

        DayOfWeek targetDayOfWeek = DayOfWeek.of(dayOfWeek);
        LocalDate resultDate = baseDate.with(targetDayOfWeek);
        resultDate = resultDate.plusWeeks(1);
        return resultDate;
    }


}
