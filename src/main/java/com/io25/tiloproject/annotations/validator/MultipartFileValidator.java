package com.io25.tiloproject.annotations.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

public class MultipartFileValidator implements ConstraintValidator<ValidMultipartFile, MultipartFile> {

    private long maxSize;

    @Override
    public void initialize(ValidMultipartFile constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.maxSize = constraintAnnotation.maxSize();
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        return file != null && !file.isEmpty() && file.getSize() <= maxSize;
    }

}
