package com.roadtripmaker.service;

import com.roadtripmaker.domain.model.Poste;
import com.roadtripmaker.domain.repository.PosteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PosteServiceImpl implements PosteService {
    private final PosteRepository posteRepository;

    @Override public Poste create(Poste poste) {
        log.info("Sauvegarde d'un nouveau poste  : {} en base de données.", poste.getUuid());

        return this.posteRepository.save(poste);
    }

    @Override public Poste getPoste(UUID uuid) {
        log.info("Recherche du poste : {}", uuid);

        return this.posteRepository.findByUuid(uuid);
    }

    @Override public List<Poste> getPostes() {
        log.info("Recherche de tous les utilisateurs");

        return this.posteRepository.findAll();
    }

    @Override public Boolean deletePoste(UUID uuid) {
        log.info("Suppression du poste : {}", uuid);

        this.posteRepository.deleteById(uuid);

        return true;
    }
}
