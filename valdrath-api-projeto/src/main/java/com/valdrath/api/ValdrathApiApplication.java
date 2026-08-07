package com.valdrath.api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.valdrath.api.Principal.Principal;

import static com.valdrath.api.Principal.Principal.delay;

@SpringBootApplication
public class ValdrathApiApplication implements CommandLineRunner {

    private final Principal principal;


    public ValdrathApiApplication(Principal principal) {
        this.principal = principal;
    }

    public static void main(String[] args) {

        SpringApplication.run(ValdrathApiApplication.class, args);
    }

    @Override
    public void run(String... args) {

        principal.rodandoJogo();

        delay(500);

        System.out.println("FIM DO PROGRAMA");

        System.exit(0);
    }
}