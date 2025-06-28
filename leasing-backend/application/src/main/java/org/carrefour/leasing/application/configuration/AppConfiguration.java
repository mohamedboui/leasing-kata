package org.carrefour.leasing.application.configuration;

import org.carrefour.leasing.core.domain.provider.CarProvider;
import org.carrefour.leasing.core.domain.provider.CustomerProvider;
import org.carrefour.leasing.core.domain.provider.LeaseProvider;
import org.carrefour.leasing.core.service.CustomerEligibilityService;
import org.carrefour.leasing.core.service.LeaseUseCaseImpl;
import org.carrefour.leasing.core.usecase.LeaseUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    CustomerEligibilityService customerEligibilityService() {
        return new CustomerEligibilityService();
    }

    @Bean
    LeaseUseCase leaseUseCase(CarProvider carProvider, CustomerProvider customerProvider, LeaseProvider leaseProvider, CustomerEligibilityService customerEligibilityService) {
        return new LeaseUseCaseImpl(carProvider, customerProvider, leaseProvider, customerEligibilityService);
    }
}
