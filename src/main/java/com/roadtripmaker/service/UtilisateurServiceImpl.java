package com.roadtripmaker.service;

import com.roadtripmaker.domain.Role;
import com.roadtripmaker.domain.Utilisateur;
import com.roadtripmaker.domain.repository.RoleRepository;
import com.roadtripmaker.domain.repository.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UtilisateurServiceImpl implements UtilisateurService {
    private final RoleRepository roleRepository;
    private final UtilisateurRepository utilisateurRepository;

    @Override
    public Utilisateur signUp(Utilisateur utilisateur) {
        log.info("Sauvegarde d'un nouvel utilisteur {} en base de données.", utilisateur.getMail());

        return this.utilisateurRepository.save(utilisateur);
    }

    @Override
    public Role addRole(Role role) {
        log.info("Sauvegarde d'un nouveau rôle {}", role.getLibelle());

        return this.roleRepository.save(role);
    }

    @Override
    public void assignRoleToAnUser(String mail, String libelle) {
        log.info("Ajout du rôle {} à l'utilisateur {}", libelle, mail);

        Utilisateur utilisateur = this.utilisateurRepository.findByMail(mail);
        Role role = this.roleRepository.findByLibelle(libelle);
        utilisateur.getRoles().add(role);
    }

    @Override
    public Utilisateur getUtilisateur(String mail) {
        log.info("Recherche de l'utilisateur {}", mail);

        return this.utilisateurRepository.findByMail(mail);
    }

    @Override
    public List<Utilisateur> getUtilisateurs() {
        log.info("Recherche de tous les utilisateurs");

        return this.utilisateurRepository.findAll();
    }
}
