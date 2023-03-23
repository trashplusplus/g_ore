package com.example.g_ore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.example.g_ore")
public class GOreApplication {

    public static void main(String[] args) {
        SpringApplication.run(GOreApplication.class, args);
    }

}
