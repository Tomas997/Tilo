package com.io25.tiloproject.mappers;

import com.io25.tiloproject.model.Coach;
import com.io25.tiloproject.services.CoachService;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CoachQualifier {
    private final CoachService coachService;

    @Autowired
    public CoachQualifier(CoachService coachService) {
        this.coachService = coachService;
    }

    @Named("stringToCoach")
    public Coach stringToCoach(String coachId) {
        Optional<Coach> byId = coachService.findById(Long.parseLong(coachId));
        byId.ifPresent(
                System.out::println
        );
        byId.ifPresentOrElse(
                System.out::println,
                ()-> System.out.println("OOPS"+coachId)
        );
        return byId.get();
    }
}
