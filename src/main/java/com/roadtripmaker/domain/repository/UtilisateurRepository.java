package com.roadtripmaker.domain.repository;

import com.roadtripmaker.domain.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    Utilisateur findByMail(String mail);
}
