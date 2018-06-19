package com.ninty8point6.connectfour;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


/**
 * The type Connect four vijay application.
 */
@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories
public class ConnectFourApplication {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(ConnectFourApplication.class, args);
    }
}