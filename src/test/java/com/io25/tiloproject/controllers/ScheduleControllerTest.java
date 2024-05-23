package com.io25.tiloproject.controllers;

import com.io25.tiloproject.dto.ScheduleWeekRecordDTO;
import com.io25.tiloproject.model.ScheduleWeekRecord;
import com.io25.tiloproject.services.ScheduleWeekItemService;
import com.io25.tiloproject.services.ScheduleWeekRecordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.BindingResult;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ScheduleControllerTest {

    @Mock
    private ScheduleWeekRecordService scheduleWeekRecordService;

    @Mock
    private ScheduleWeekItemService scheduleWeekItemService;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private ScheduleController scheduleController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testHandleFormUpload() throws IOException {
        ScheduleWeekRecordDTO scheduleWeekRecordDTO = new ScheduleWeekRecordDTO();
        scheduleWeekRecordDTO.setDay(1);
        scheduleWeekRecordDTO.setCoach("John Doe");

        when(scheduleWeekRecordService.saveNewScheduleWeekRecord(any(ScheduleWeekRecordDTO.class))).thenReturn(new ScheduleWeekRecord());

        scheduleController.handleFormUpload(scheduleWeekRecordDTO, bindingResult);

        verify(scheduleWeekRecordService, times(1)).saveNewScheduleWeekRecord(any(ScheduleWeekRecordDTO.class));
        verify(scheduleWeekItemService, times(1)).saveScheduleItems(any(), any());
    }

    @Test
    void testHandleFormUploadException() throws IOException {
        ScheduleWeekRecordDTO scheduleWeekRecordDTO = new ScheduleWeekRecordDTO();
        scheduleWeekRecordDTO.setDay(1);
        scheduleWeekRecordDTO.setCoach("John Doe");

        when(scheduleWeekRecordService.saveNewScheduleWeekRecord(any(ScheduleWeekRecordDTO.class))).thenThrow(new RuntimeException());

        scheduleController.handleFormUpload(scheduleWeekRecordDTO, bindingResult);

        verify(scheduleWeekRecordService, times(1)).saveNewScheduleWeekRecord(any(ScheduleWeekRecordDTO.class));
        verify(scheduleWeekItemService, never()).saveScheduleItems(any(), any());
    }

}
