package com.roadtripmaker.service;

import com.roadtripmaker.domain.model.Role;
import com.roadtripmaker.domain.model.User;
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
    public User signUp(User user) {
        log.info("Sauvegarde d'un nouvel utilisteur {} en base de données.", user.getMail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return this.userRepository.save(user);
    }

    @Override
    public Role addRole(Role role) {
        log.info("Sauvegarde d'un nouveau rôle {}", role.getLibelle());

        return this.roleRepository.save(role);
    }

    @Override
    public void assignRoleToAnUser(String mail, String nameRole) {
        log.info("Ajout du rôle {} à l'utilisateur {}", nameRole, mail);

        User user = this.userRepository.findByMail(mail);
        Role role = this.roleRepository.findByLibelle(nameRole);
        user.getRoles().add(role);
    }

    @Override
    public User getUser(String mail) {
        log.info("Recherche de l'utilisateur {}", mail);

        return this.userRepository.findByMail(mail);
    }

    @Override
    public List<User> getUsers() {
        log.info("Recherche de tous les utilisateurs");

        return this.userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        User user = userRepository.findByMail(mail);

        if (user == null) {
            log.error("L'utilisateur {} n'a pas été trouvé.", mail);
            throw new UsernameNotFoundException("L'utilisateur n'a pas été trouvé.");
        } else {
            log.info("L'utilisateur {} a été trouvé", mail);
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getLibelle()));
        });

        return new org.springframework.security.core.userdetails.User(user.getMail(), user.getPassword(),
                authorities);
    }
}
