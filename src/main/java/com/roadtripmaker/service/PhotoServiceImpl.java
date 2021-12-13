package com.roadtripmaker.service;


import com.roadtripmaker.domain.model.Photo;
import com.roadtripmaker.domain.repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PhotoServiceImpl implements PhotoService {
    private final PhotoRepository photoRepository;

    @Override
    public Photo savePhoto(MultipartFile file) throws IOException {
        Photo photo = new Photo();
        log.info("Sauvegarde d'une nouvelle photo  : {} en base de données.", photo.getUuid());

        photo.setName(StringUtils.cleanPath(file.getOriginalFilename()));
        photo.setContentType(file.getContentType());
        photo.setData(file.getBytes());
        photo.setSize(file.getSize());

        return photoRepository.save(photo);
    }

    @Override
    public Photo getPhoto(UUID uuid) {
        log.info("Recherche de la photo : {}", uuid);

        return this.photoRepository.findByUuid(uuid);
    }

    @Override
    public List<Photo> getPhotos() {
        log.info("Recherche de toutes les photos");

        return this.photoRepository.findAll();
    }
}
