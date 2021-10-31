package com.roadtripmaker;

import com.roadtripmaker.domain.Role;
import com.roadtripmaker.domain.Utilisateur;
import com.roadtripmaker.service.UtilisateurService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
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
}
