package com.labs.tenderservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@OpenAPIDefinition(
        info = @Info(

                title = "Tender Service Demo",
                description = "Created by: Maksym Mishchuk, Eugene Galagan, Zaslavsky Vadym",
                version = "lab6"
        ),
        servers = {

                @Server(url = "http://localhost:8080", description = "labs server"),
        }

)

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories
public class TenderServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(TenderServiceApplication.class, args);
    }
}
