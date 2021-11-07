package com.roadtripmaker.service;

import com.roadtripmaker.domain.model.Poste;

import java.util.List;
import java.util.UUID;

public interface PosteService {
    Poste create(Poste poste);

    Poste getPoste(UUID uuid);

    List<Poste> getPostes();
}
