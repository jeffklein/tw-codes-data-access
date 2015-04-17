package org.jeffklein.turfwars.codes.dataaccess.config;

import org.jeffklein.turfwars.codes.client.TurfWarsApiClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * This class only exists so that ComponentScan can do its thang.
 */
@Configuration
@ComponentScan(basePackages = "org.jeffklein.turfwars.codes")
public class SpringConfiguration {
    @Bean(name = "turfWarsApiClient")
    public TurfWarsApiClient getTurfWarsApiClient() {
        return new TurfWarsApiClient();
    }
}
