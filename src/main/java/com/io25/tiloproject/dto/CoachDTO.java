package com.io25.tiloproject.dto;

import com.io25.tiloproject.annotations.validator.ValidMultipartFile;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CoachDTO {

    @NotBlank
    private String fullName;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String info;


    //custom annotation
    @ValidMultipartFile(maxSize = 500*1024)
    private MultipartFile img;
}
