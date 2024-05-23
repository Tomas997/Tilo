package com.io25.tiloproject.services.impl;

import com.io25.tiloproject.dto.ScheduleWeekRecordDTO;
import com.io25.tiloproject.mappers.ScheduleWeekItemMapper;
import com.io25.tiloproject.model.ScheduleWeekItem;
import com.io25.tiloproject.model.ScheduleWeekRecord;
import com.io25.tiloproject.repository.ScheduleWeekItemRepository;
import com.io25.tiloproject.services.ScheduleWeekItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ScheduleWeekItemServiceImpl  implements ScheduleWeekItemService {
    private final ScheduleWeekItemRepository scheduleWeekItemRepository;

    @Override
    public void deleteById(Long id) {
        scheduleWeekItemRepository.deleteById(id);
    }

    @Override
    public List<ScheduleWeekItem> findAllByRecordId(Long id) {
        return scheduleWeekItemRepository.findAllByScheduleWeekRecordId(id);
    }

    @Override
    public void saveScheduleItems(List<ScheduleWeekRecordDTO.ScheduleWeekItemDTO> items, ScheduleWeekRecord scheduleWeekRecord) {
        List<ScheduleWeekItem> scheduleWeekItems = ScheduleWeekItemMapper.INSTANCE.toEntity(items);
        scheduleWeekItems.forEach(item ->{
            item.setScheduleWeekRecord(scheduleWeekRecord);
        });
        scheduleWeekItemRepository.saveAll(scheduleWeekItems);
    }
}
