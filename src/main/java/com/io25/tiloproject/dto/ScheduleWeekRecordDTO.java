package com.io25.tiloproject.dto;


import lombok.Data;
import java.util.List;

@Data
public class ScheduleWeekRecordDTO {
    private Integer day;
    private String coach;
    private List<ScheduleWeekItemDTO> items;
    @Data
    public static class ScheduleWeekItemDTO {
        private String time;
        private Integer service;
    }
}
