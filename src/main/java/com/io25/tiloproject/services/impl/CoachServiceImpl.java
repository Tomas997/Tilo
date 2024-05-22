package com.io25.tiloproject.services.impl;

import com.io25.tiloproject.dto.CoachDTO;
import com.io25.tiloproject.mappers.CoachMapper;
import com.io25.tiloproject.model.Coach;
import com.io25.tiloproject.model.Role;
import com.io25.tiloproject.repository.CoachRepository;
import com.io25.tiloproject.services.CoachService;
import com.io25.tiloproject.services.ImageService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.print.DocFlavor;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CoachServiceImpl implements CoachService {
    PasswordEncoder passwordEncoder;
    private final CoachRepository coachRepository;
    private final ImageService imageService;
    @Override
    public List<Coach> getAllCoaches() {
        return coachRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        coachRepository.findById(id).ifPresent(
                coach -> {
                    coachRepository.delete(coach);
                    imageService.removeImage(getCoachImageName(coach));
                }
        );
    }

    @Transactional
    @Override
    public Coach saveNewCoach(CoachDTO coachDTO) throws IOException {
        coachDTO.setPassword(passwordEncoder.encode(coachDTO.getPassword()));
        Coach coach = CoachMapper.INSTANCE.dtoToEntity(coachDTO);
        coach.setImgName(getCoachImageName(coach));
        coach.setRole(Role.ROLE_COACH);
        coachRepository.save(coach);
        imageService.saveImage(getCoachImageName(coach), coachDTO.getImg().getBytes());
        return coach;
    }

    @Override
    public Optional<Coach> findFirstCoach() {
        return coachRepository.findFirst();
    }

    @Override
    public Optional<Coach> findById(Long id) {
        return coachRepository.findById(id);
    }

    private String getCoachImageName(Coach coach){
        String fileName = coach.getImgName();
        String extension = fileName.substring(fileName.lastIndexOf('.'));
        return "coach_"+ coach.getUsername()+extension;
    }
}
