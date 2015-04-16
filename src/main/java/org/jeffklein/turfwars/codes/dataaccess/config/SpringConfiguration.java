package org.jeffklein.turfwars.codes.dataaccess.config;

import org.jeffklein.turfwars.codes.client.TempCodeApiClient;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * This class only exists so that ComponentScan can do its thang.
 */
@Configuration
@ComponentScan(basePackages = "org.jeffklein.turfwars.*")
public class SpringConfiguration {
    // Could configure some beans here (MessageSource, PropertySourcesPlaceHolderConfigurer, etc)

    @Bean
    public TempCodeApiClient getTempCodeApiClient() {
        return new TempCodeApiClient();
    }
}
