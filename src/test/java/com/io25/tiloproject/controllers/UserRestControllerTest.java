package com.io25.tiloproject.controllers;

import com.io25.tiloproject.repository.ScheduleItemRepository;
import com.io25.tiloproject.repository.TiloUserRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserRestControllerTest {

    @Mock
    private TiloUserRepository userRepository;

    @Mock
    private ScheduleItemRepository scheduleItemRepository;

    @InjectMocks
    private UserRestController userRestController;

//    @Test
//    void getSubscribed_ReturnsSubscribedUsers() {
//        // Arrange
//        Long scheduleItemId = 1L;
//        ScheduleItem scheduleItem = new ScheduleItem();
//        scheduleItem.setId(scheduleItemId);
//
//        TiloUser user1 = new TiloUser();
//        user1.setId(1L);
//        TiloUser user2 = new TiloUser();
//        user2.setId(2L);
//        List<TiloUser> users = Arrays.asList(user1, user2);
//
//        when(scheduleItemRepository.findById(scheduleItemId)).thenReturn(Optional.of(scheduleItem));
//        when(userRepository.findAllByScheduleItemsContaining(scheduleItem)).thenReturn(users);
//
//        // Act
//        List<TiloUserRestDTO> result = userRestController.getSubscribed(scheduleItemId);
//
//        // Assert
//        assertEquals(users.size(), result.size());
//        for (int i = 0; i < users.size(); i++) {
//            assertEquals(users.get(i).getId(), result.get(i).getId());
//        }
//
//        verify(scheduleItemRepository, times(1)).findById(scheduleItemId);
//        verify(userRepository, times(1)).findAllByScheduleItemsContaining(scheduleItem);
//    }

//    @Test
//    void getSubscribed_ThrowsException_WhenScheduleItemNotFound() {
//        // Arrange
//        Long scheduleItemId = 1L;
//        when(scheduleItemRepository.findById(scheduleItemId)).thenReturn(Optional.empty());
//
//        // Act & Assert
//        ResponseStatusException exception = org.junit.jupiter.api.Assertions.assertThrows(ResponseStatusException.class,
//                () -> userRestController.getSubscribed(scheduleItemId));
//        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
//
//        verify(userRepository, never()).findAllByScheduleItemsContaining(any());
//    }
}
