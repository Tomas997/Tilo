package com.io25.tiloproject.services;

public interface ScheduleItemService {

    void bookScheduleItem(Long id, Long scheduleItemId);

    void unBookScheduleItem(Long id, Long scheduleItemId);
}
