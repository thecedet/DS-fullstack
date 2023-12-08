package fr.polytech.dsbackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("*");
		// On est obligé de mettre le allowedMethods sinon les requêtes PUT ne passe pas
		// Sinon juste autoriser les méthodes GET, PUT, POST, DELETE
		// Juste autoriser angular => http://localhost:4200
    }
}