package com.roadtripmaker;

import com.roadtripmaker.domain.model.Poste;
import com.roadtripmaker.domain.model.Role;
import com.roadtripmaker.domain.model.Utilisateur;
import com.roadtripmaker.domain.repository.PosteRepository;
import com.roadtripmaker.service.UtilisateurService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Date;

@SpringBootApplication
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(UtilisateurService utilisateurService) {
        return args -> {
            utilisateurService.addRole(new Role(null, "ROLE_USER"));
            utilisateurService.addRole(new Role(null, "ADMIN"));

            utilisateurService.signUp(
                    new Utilisateur(null, "Tiziano", "Ghisotti", "tiziano.ghisotti@gmail.com", "azerty", new ArrayList<>()));

            utilisateurService.assignRoleToAnUser("tiziano.ghisotti@gmail.com", "ADMIN");
        };
    }

    @Bean
    CommandLineRunner commandLineRunner(PosteRepository posteRepository) {
        return args -> {
            posteRepository.save(new Poste(null, "Ceci est le titre #1", "Ceci est la description #1", new Date(), new Date()));
            posteRepository.save(new Poste(null, "Ceci est le titre #2", "Ceci est la description #2", new Date(), new Date()));
            posteRepository.save(new Poste(null, "Ceci est le titre #3", "Ceci est la description #3", new Date(), new Date()));
            posteRepository.save(new Poste(null, "Ceci est le titre #4", "Ceci est la description #4", new Date(), new Date()));
            posteRepository.save(new Poste(null, "Ceci est le titre #5", "Ceci est la description #5", new Date(), new Date()));
            posteRepository.save(new Poste(null, "Ceci est le titre #6", "Ceci est la description #6", new Date(), new Date()));
        };
    }
}
