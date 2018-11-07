package com.akandouch.invoicec.microservice.login.conf;

import com.akandouch.invoicec.microservice.login.User;
import com.akandouch.invoicec.microservice.login.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.UUID;

import static com.akandouch.invoicec.microservice.login.User.Role.ADMIN;
import static com.akandouch.invoicec.microservice.login.User.Role.USER;

@Configuration
@Profile("dev")
@Slf4j
public class UserFixture implements CommandLineRunner {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserFixture(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        userRepository.deleteAll();
        log.info("save one user and one admin");
        userRepository.save(User.builder()
                .roles(Arrays.asList(ADMIN, USER))
                .id(UUID.randomUUID().toString())
                .password(passwordEncoder.encode("admin"))
                .username("admin")
                .build()
        );
        log.info("user: admin, password: admin");
        userRepository.save(User.builder()
                .roles(Arrays.asList(USER))
                .id(UUID.randomUUID().toString())
                .password(passwordEncoder.encode("user"))
                .username("user")
                .build()
        );
        log.info("user: user, password: user");

    }
}
