package com.io25.tiloproject.services;

import com.io25.tiloproject.dto.ScheduleWeekRecordDTO;
import com.io25.tiloproject.model.ScheduleWeekItem;
import com.io25.tiloproject.model.ScheduleWeekRecord;
import com.io25.tiloproject.repository.ScheduleWeekItemRepository;
import com.io25.tiloproject.services.impl.ScheduleWeekItemServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

class ScheduleWeekItemServiceImplTest {

    @Mock
    private ScheduleWeekItemRepository scheduleWeekItemRepository;

    @InjectMocks
    private ScheduleWeekItemServiceImpl scheduleWeekItemService;

    public ScheduleWeekItemServiceImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeleteById() {
        Long id = 1L;
        scheduleWeekItemService.deleteById(id);
        verify(scheduleWeekItemRepository, times(1)).deleteById(id);
    }

    @Test
    void testFindAllByRecordId() {
        Long id = 1L;
        when(scheduleWeekItemRepository.findAllByScheduleWeekRecordId(id)).thenReturn(Collections.emptyList());

        List<ScheduleWeekItem> result = scheduleWeekItemService.findAllByRecordId(id);

        verify(scheduleWeekItemRepository, times(1)).findAllByScheduleWeekRecordId(id);
        assert (result.isEmpty());
    }

    @Test
    void testSaveScheduleItems() {
        ScheduleWeekRecordDTO.ScheduleWeekItemDTO itemDTO = new ScheduleWeekRecordDTO.ScheduleWeekItemDTO();
        itemDTO.setTime("10:00"); // Встановлюємо значення для поля "time"
        itemDTO.setService(1); // Встановлюємо значення для поля "service"
        List<ScheduleWeekRecordDTO.ScheduleWeekItemDTO> itemsDTO = Collections.singletonList(itemDTO);
        ScheduleWeekRecord scheduleWeekRecord = new ScheduleWeekRecord();

        scheduleWeekItemService.saveScheduleItems(itemsDTO, scheduleWeekRecord);

        verify(scheduleWeekItemRepository, times(1)).saveAll(anyList());
    }


}
