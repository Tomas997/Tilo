package com.io25.tiloproject.controllers;

import com.io25.tiloproject.dto.TiloUserRestDTO;
import com.io25.tiloproject.mappers.TiloUserMapper;
import com.io25.tiloproject.model.ScheduleItem;
import com.io25.tiloproject.model.TiloUser;
import com.io25.tiloproject.repository.ScheduleItemRepository;
import com.io25.tiloproject.repository.TiloUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/user_api")
@RequiredArgsConstructor
public class UserRestController {
    private final TiloUserRepository userRepository;
    private final ScheduleItemRepository scheduleItemRepository;

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/get/{id}")
    public List<TiloUserRestDTO> getSubscribed(@PathVariable Long id) {
        ScheduleItem scheduleItem = scheduleItemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Schedule item not found"));
        List<TiloUser> allByScheduleItem = userRepository.findAllByScheduleItemsContaining(scheduleItem);
        return TiloUserMapper.INSTANCE.toDTO(allByScheduleItem);
    }
}
