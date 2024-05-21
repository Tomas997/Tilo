package com.io25.tiloproject.services.impl;

import com.io25.tiloproject.model.ScheduleItem;
import com.io25.tiloproject.model.TiloUser;
import com.io25.tiloproject.repository.ScheduleItemRepository;
import com.io25.tiloproject.repository.TiloUserRepository;
import com.io25.tiloproject.services.ScheduleItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ScheduleItemServiceImpl implements ScheduleItemService {

    private final TiloUserRepository tiloUserRepository;
    private final ScheduleItemRepository scheduleItemRepository;

    @Override
    public void bookScheduleItem(Long id, Long scheduleItemId) {
        TiloUser user = tiloUserRepository.findTiloUserById(id).orElseThrow(() -> new RuntimeException("Користувача не знайдено"));
        ScheduleItem scheduleItem = scheduleItemRepository.findById(scheduleItemId).orElseThrow(() -> new RuntimeException("Заняття не знайдено"));

        if (user.getScheduleItems().contains(scheduleItem)) {
            return;
        }

        scheduleItem.setQuantity(scheduleItem.getQuantity() + 1);
        user.getScheduleItems().add(scheduleItem);
        scheduleItemRepository.save(scheduleItem);
        tiloUserRepository.save(user);
    }

    @Override
    public void unBookScheduleItem(Long id, Long scheduleItemId) {
        TiloUser user = tiloUserRepository.findTiloUserById(id).orElseThrow(() -> new RuntimeException("Користувача не знайдено"));
        ScheduleItem scheduleItem = scheduleItemRepository.findById(scheduleItemId).orElseThrow(() -> new RuntimeException("Заняття не знайдено"));

        if (!user.getScheduleItems().contains(scheduleItem)) {
            return;
        }
        scheduleItem.setQuantity(scheduleItem.getQuantity() - 1);
        user.getScheduleItems().remove(scheduleItem);
        scheduleItemRepository.save(scheduleItem);
        tiloUserRepository.save(user);
    }
}

