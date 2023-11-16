package com.labs.tenderservice;

import com.labs.tenderservice.repository.TenderRepository;
import com.labs.tenderservice.repository.TenderURLRepository;
import com.labs.tenderservice.service.TenderService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import oracle.jdbc.pool.OracleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootApplication
public class TenderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TenderServiceApplication.class, args);
    }

    @Bean
    TenderService tenderService(TenderRepository tenderRepository, TenderURLRepository tenderURLRepository) {
        return new TenderService(tenderRepository, tenderURLRepository);
    }

}

