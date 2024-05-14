package com.io25.tiloproject.mappers;

import com.io25.tiloproject.model.Coach;
import com.io25.tiloproject.services.CoachService;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CoachQualifier {
    private final CoachService coachService;

    @Autowired
    public CoachQualifier(CoachService coachService) {
        this.coachService = coachService;
    }

    @Named("stringToCoach")
    public Coach stringToCoach(String coachId) {
        return coachService.findById(Long.parseLong(coachId)).get();
    }
}
