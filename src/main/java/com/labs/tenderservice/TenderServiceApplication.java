package com.labs.tenderservice;

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

    @Bean
    DataSource dataSource() throws SQLException {
        OracleDataSource dataSource = new OracleDataSource();
        dataSource.setUser("SYSTEM");
        dataSource.setPassword("159085");
        dataSource.setURL("jdbc:oracle:thin:@localhost:1521/XEPDB1");
        dataSource.setImplicitCachingEnabled(true);

        return dataSource;
    }
}

