package com.roadtripmaker.service;

import com.roadtripmaker.domain.model.Role;
import com.roadtripmaker.domain.model.Utilisateur;
import com.roadtripmaker.domain.repository.RoleRepository;
import com.roadtripmaker.domain.repository.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UtilisateurServiceImpl implements UtilisateurService, UserDetailsService {
    private final RoleRepository roleRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Utilisateur signUp(Utilisateur utilisateur) {
        log.info("Sauvegarde d'un nouvel utilisteur {} en base de données.", utilisateur.getMail());
        utilisateur.setMotDePasse(passwordEncoder.encode(utilisateur.getMotDePasse()));

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

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurRepository.findByMail(mail);

        if (utilisateur == null) {
            log.error("L'utilisateur {} n'a pas été trouvé.", mail);
            throw new UsernameNotFoundException("L'utilisateur n'a pas été trouvé.");
        } else {
            log.info("L'utilisateur {} a été trouvé", mail);
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        utilisateur.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getLibelle()));
        });

        return new org.springframework.security.core.userdetails.User(utilisateur.getMail(), utilisateur.getMotDePasse(),
                authorities);
    }
}
