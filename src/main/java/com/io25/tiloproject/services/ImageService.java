package com.io25.tiloproject.services;

import org.springframework.http.MediaType;

import java.io.IOException;

public interface ImageService {
    byte[] getImage(String imageName) throws IOException;
    MediaType getMediaType(String imageName) throws IOException;

    void saveImage(String imageName, byte[] bytes) throws IOException;

    void removeImage(String service);

}
