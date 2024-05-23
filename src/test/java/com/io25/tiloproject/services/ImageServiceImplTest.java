package com.io25.tiloproject.services;

import com.io25.tiloproject.services.impl.ImageServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class ImageServiceImplTest {

    private ImageService imageService;

    @BeforeEach
    void setUp() {
        imageService = new ImageServiceImpl();
    }

    @Test
    void testGetImage() throws IOException {
        String imageName = "test-image.jpg";
        byte[] imageData = "test".getBytes();
        Files.write(Paths.get("data/img/", imageName), imageData);

        byte[] result = imageService.getImage(imageName);

        assertArrayEquals(imageData, result);
    }

    @Test
    void testGetMediaType() throws IOException {
        String imageName = "test-image.jpg";

        MediaType mediaType = imageService.getMediaType(imageName);

        assertEquals("image/jpeg", mediaType.toString());
    }

    @Test
    void testSaveImage() throws IOException {
        String imageName = "test-image.jpg";
        byte[] imageData = "test".getBytes();

        imageService.saveImage(imageName, imageData);

        assertTrue(Files.exists(Paths.get("data/img/", imageName)));
    }

    @Test
    void testRemoveImage() throws IOException {
        String imageName = "test-image.jpg";
        byte[] imageData = "test".getBytes();
        Files.write(Paths.get("data/img/", imageName), imageData);

        imageService.removeImage(imageName);

        assertFalse(Files.exists(Paths.get("data/img/", imageName)));
    }
}
