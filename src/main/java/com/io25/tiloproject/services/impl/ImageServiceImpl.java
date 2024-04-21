package com.io25.tiloproject.services.impl;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import com.io25.tiloproject.services.ImageService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Service
public class ImageServiceImpl implements ImageService {

    private static final String SRC_IMG = "data/img/";

    @Override
    public byte[] getImage(String imageName) throws IOException {
        Path imagePath = Paths.get(SRC_IMG, imageName);
        if (Files.exists(imagePath)) {
            return Files.readAllBytes(imagePath);
        } else {
            throw new IOException("Image not found");
        }
    }

    @Override
    public MediaType getMediaType(String imageName) throws IOException {
        Path imagePath = Paths.get(SRC_IMG, imageName);
        String contentType = Files.probeContentType(imagePath);
        if (contentType == null) {
            contentType = "image/*";
        }
        return MediaType.parseMediaType(contentType);
    }

    @Override
    public void saveImage(String fileName, byte[] bytes) throws IOException {
        Path imagePath = Paths.get(SRC_IMG, fileName);
        Files.write(imagePath, bytes, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
    }

    @Override
    public void removeImage(String fileName) {
        Path imagePath = Paths.get(SRC_IMG, fileName);
        try {
            Files.delete(imagePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

