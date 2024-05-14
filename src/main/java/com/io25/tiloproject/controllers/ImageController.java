package com.io25.tiloproject.controllers;

import com.io25.tiloproject.services.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController("/")
@AllArgsConstructor
public class ImageController {

    private final ImageService imageService;


    @GetMapping(value = "src/img/{imageName}")
    public ResponseEntity<byte[]> getImage(@PathVariable String imageName) {
        try {
            byte[] imageBytes = imageService.getImage(imageName);
            MediaType mediaType = imageService.getMediaType(imageName);
            return ResponseEntity.ok().contentType(mediaType).body(imageBytes);
        } catch (IOException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
