package com.roadtripmaker;

import com.roadtripmaker.domain.model.Post;
import com.roadtripmaker.domain.model.Role;
import com.roadtripmaker.domain.model.User;
import com.roadtripmaker.domain.repository.PostRepository;
import com.roadtripmaker.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Date;

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
    CommandLineRunner run(UserService userService) {
        return args -> {
            userService.addRole(new Role(null, "ROLE_USER"));
            userService.addRole(new Role(null, "ADMIN"));

            userService.signUp(
                    new User(null, "Tiziano", "Ghisotti", "tiziano.ghisotti@gmail.com", "azerty", new ArrayList<>()));

            userService.assignRoleToAnUser("tiziano.ghisotti@gmail.com", "ADMIN");
        };
    }

    @Bean
    CommandLineRunner commandLineRunner(PostRepository postRepository) {
        return args -> {
            postRepository.save(new Post(null, "Ceci est le titre #1", "Ceci est la description #1", new Date(), new Date()));
            postRepository.save(new Post(null, "Ceci est le titre #2", "Ceci est la description #2", new Date(), new Date()));
            postRepository.save(new Post(null, "Ceci est le titre #3", "Ceci est la description #3", new Date(), new Date()));
            postRepository.save(new Post(null, "Ceci est le titre #4", "Ceci est la description #4", new Date(), new Date()));
            postRepository.save(new Post(null, "Ceci est le titre #5", "Ceci est la description #5", new Date(), new Date()));
            postRepository.save(new Post(null, "Ceci est le titre #6", "Ceci est la description #6", new Date(), new Date()));
        };
    }
}
