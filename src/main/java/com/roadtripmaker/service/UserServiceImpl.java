package com.roadtripmaker.service;

import com.roadtripmaker.domain.model.RoadUser;
import com.roadtripmaker.domain.model.Role;
import com.roadtripmaker.domain.repository.RoleRepository;
import com.roadtripmaker.domain.repository.UserRepository;
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
public class UserServiceImpl implements UserService, UserDetailsService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public RoadUser signUp(RoadUser roadUser) {
        log.info("Sauvegarde d'un nouvel utilisteur {} en base de données.", roadUser.getMail());
        roadUser.setPassword(passwordEncoder.encode(roadUser.getPassword()));

        return this.userRepository.save(roadUser);
    }

    @Override
    public Role addRole(Role role) {
        log.info("Sauvegarde d'un nouveau rôle {}", role.getLibelle());

        return this.roleRepository.save(role);
    }

    @Override
    public void assignRoleToAnUser(String mail, String nameRole) {
        log.info("Ajout du rôle {} à l'utilisateur {}", nameRole, mail);

        RoadUser roadUser = this.userRepository.findByMail(mail);
        Role role = this.roleRepository.findByLibelle(nameRole);
        roadUser.getRoles().add(role);
    }

    @Override
    public RoadUser getUser(String mail) {
        log.info("Recherche de l'utilisateur {}", mail);

        return this.userRepository.findByMail(mail);
    }

    @Override
    public List<RoadUser> getUsers() {
        log.info("Recherche de tous les utilisateurs");

        return this.userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        RoadUser roadUser = userRepository.findByMail(mail);

        if (roadUser == null) {
            log.error("L'utilisateur {} n'a pas été trouvé.", mail);
            throw new UsernameNotFoundException("L'utilisateur n'a pas été trouvé.");
        } else {
            log.info("L'utilisateur {} a été trouvé", mail);
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        roadUser.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getLibelle()));
        });

        return new org.springframework.security.core.userdetails.User(roadUser.getMail(), roadUser.getPassword(),
                authorities);
    }
}
