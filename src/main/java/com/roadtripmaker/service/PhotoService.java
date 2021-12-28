package com.roadtripmaker.service;

import com.roadtripmaker.domain.model.Photo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface PhotoService {

    Photo savePhoto(MultipartFile file) throws IOException;

    Photo getPhoto(String name);

    List<Photo> getPhotos();
}
