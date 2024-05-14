package com.io25.tiloproject.services.impl;

import com.io25.tiloproject.dto.CoachDTO;
import com.io25.tiloproject.mappers.CoachMapper;
import com.io25.tiloproject.model.Coach;
import com.io25.tiloproject.repository.CoachRepository;
import com.io25.tiloproject.services.CoachService;
import com.io25.tiloproject.services.ImageService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CoachServiceImpl implements CoachService {
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
        Coach coach = CoachMapper.INSTANCE.dtoToEntity(coachDTO);
        coachRepository.save(coach);
        coach.setImgName(getCoachImageName(coach));
        imageService.saveImage(getCoachImageName(coach),coachDTO.getImg().getBytes());
        return coach;
    }

    @Override
    public Optional<Coach> findFirstCoach() {
        return coachRepository.findFirst();
    }

    @Override
    public Optional<Coach> findById(long id) {
        return coachRepository.findById(id);
    }

    private String getCoachImageName(Coach coach){
        String fileName = coach.getImgName();
        String extension = fileName.substring(fileName.lastIndexOf('.'));
        return "coach_"+coach.getId()+extension;
    }
}
