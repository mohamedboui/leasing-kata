package org.carrefour.leasing.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication(scanBasePackages = "org.carrefour.leasing")
@EnableR2dbcRepositories(basePackages = "org.carrefour.leasing.dataprovider.repository")

public class LeasingApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeasingApplication.class, args);
    }

}
