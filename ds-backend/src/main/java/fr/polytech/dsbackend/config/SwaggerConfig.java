package fr.polytech.dsbackend.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
            .group("Série - API")
            .pathsToMatch("/**")
            .build();
    }

    /*
     * URL
     * http://localhost:8080/swagger-ui/index.html
     */

}
