package com.io25.tiloproject.dto;

import com.io25.tiloproject.annotations.validator.ValidMultipartFile;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class CoachDTO {

    @NotBlank
    private String fullName;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String info;
    @Column(unique = true)
    private String username;

    private String password;


    //custom annotation
    @ValidMultipartFile(maxSize = 500*1024)
    private MultipartFile img;
}
