package com.io25.tiloproject.services;

import com.io25.tiloproject.model.ScheduleItem;
import com.io25.tiloproject.model.TiloUser;
import com.io25.tiloproject.repository.ScheduleItemRepository;
import com.io25.tiloproject.repository.TiloUserRepository;
import com.io25.tiloproject.services.impl.ScheduleItemServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ScheduleItemServiceImplTest {

    private ScheduleItemServiceImpl scheduleItemService;
    private TiloUserRepository tiloUserRepository;
    private ScheduleItemRepository scheduleItemRepository;

    @BeforeEach
    void setUp() {
        tiloUserRepository = mock(TiloUserRepository.class);
        scheduleItemRepository = mock(ScheduleItemRepository.class);
        scheduleItemService = new ScheduleItemServiceImpl(tiloUserRepository, scheduleItemRepository);
    }





    @Test
    void testUnBookScheduleItem() {
        Long userId = 1L;
        Long scheduleItemId = 2L;
        TiloUser user = new TiloUser();
        user.setId(userId);
        ScheduleItem scheduleItem = new ScheduleItem();
        scheduleItem.setId(scheduleItemId);
        scheduleItem.setQuantity(1);
        List<ScheduleItem> userScheduleItems = new ArrayList<>();
        userScheduleItems.add(scheduleItem);
        user.setScheduleItems(userScheduleItems);
        when(tiloUserRepository.findTiloUserById(userId)).thenReturn(Optional.of(user));
        when(scheduleItemRepository.findById(scheduleItemId)).thenReturn(Optional.of(scheduleItem));

        scheduleItemService.unBookScheduleItem(userId, scheduleItemId);

        assertEquals(0, scheduleItem.getQuantity());
        assertEquals(0, user.getScheduleItems().size());
        verify(scheduleItemRepository, times(1)).save(scheduleItem);
        verify(tiloUserRepository, times(1)).save(user);
    }


    @Test
    void testBookScheduleItemNotFoundUser() {
        Long userId = 1L;
        Long scheduleItemId = 2L;
        when(tiloUserRepository.findTiloUserById(userId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> scheduleItemService.bookScheduleItem(userId, scheduleItemId));
    }

    @Test
    void testBookScheduleItemNotFoundScheduleItem() {
        Long userId = 1L;
        Long scheduleItemId = 2L;
        TiloUser user = new TiloUser();
        user.setId(userId);
        when(tiloUserRepository.findTiloUserById(userId)).thenReturn(Optional.of(user));
        when(scheduleItemRepository.findById(scheduleItemId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> scheduleItemService.bookScheduleItem(userId, scheduleItemId));
    }

    @Test
    void testUnBookScheduleItemNotFoundUser() {
        Long userId = 1L;
        Long scheduleItemId = 2L;
        when(tiloUserRepository.findTiloUserById(userId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> scheduleItemService.unBookScheduleItem(userId, scheduleItemId));
    }

    @Test
    void testUnBookScheduleItemNotFoundScheduleItem() {
        Long userId = 1L;
        Long scheduleItemId = 2L;
        TiloUser user = new TiloUser();
        user.setId(userId);
        when(tiloUserRepository.findTiloUserById(userId)).thenReturn(Optional.of(user));
        when(scheduleItemRepository.findById(scheduleItemId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> scheduleItemService.unBookScheduleItem(userId, scheduleItemId));
    }
}
