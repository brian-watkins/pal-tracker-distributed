package io.pivotal.pal.tracker.registration;

import io.pivotal.spring.cloud.service.config.ConfigClientOAuth2BootstrapConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import java.util.TimeZone;


@SpringBootApplication
@ComponentScan({
    "io.pivotal.pal.tracker.accounts",
    "io.pivotal.pal.tracker.restsupport",
    "io.pivotal.pal.tracker.projects",
    "io.pivotal.pal.tracker.users",
    "io.pivotal.pal.tracker.registration"
})
@EnableEurekaClient
@EnableResourceServer
@EnableWebSecurity
public class App {
    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        SpringApplication.run(App.class, args);
    }

    @Bean
    @Primary
    @ConditionalOnBean(ConfigClientOAuth2BootstrapConfiguration.class)
    public OAuth2ProtectedResourceDetails details(ConfigClientOAuth2BootstrapConfiguration configClientConfig) {
        return configClientConfig.configClientOAuth2ResourceDetails();
    }
}
