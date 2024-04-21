package com.io25.tiloproject.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import com.io25.tiloproject.annotations.validator.ValidMultipartFile;

@Data
public class YogaServiceDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String shortDescription;
    @NotBlank
    private String description;
    @NotBlank
    private String price;

    //custom annotation
    @ValidMultipartFile(maxSize = 500*1024)
    private MultipartFile img;
}
