package com.io25.tiloproject.controllers;

import com.io25.tiloproject.services.ImageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class ImageControllerTest {

    @Mock
    private ImageService imageService;

    @InjectMocks
    private ImageController imageController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetImage_Success() throws IOException {
        String imageName = "testImage.jpg";
        byte[] imageBytes = new byte[]{1, 2, 3};
        MediaType mediaType = MediaType.IMAGE_JPEG;

        when(imageService.getImage(imageName)).thenReturn(imageBytes);
        when(imageService.getMediaType(imageName)).thenReturn(mediaType);

        ResponseEntity<byte[]> responseEntity = imageController.getImage(imageName);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(responseEntity.getHeaders().getContentType()).isEqualTo(mediaType);
        assertThat(responseEntity.getBody()).isEqualTo(imageBytes);
    }

    @Test
    void testGetImage_NotFound() throws IOException {
        String imageName = "nonExistentImage.jpg";

        when(imageService.getImage(imageName)).thenThrow(new IOException());

        ResponseEntity<byte[]> responseEntity = imageController.getImage(imageName);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(404);
        assertThat(responseEntity.getBody()).isNull();
    }
}
