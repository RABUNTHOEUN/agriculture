package com.thoeun.agriculture.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Apply to all paths
                        .allowedOrigins("*") // or .allowedOrigins("http://localhost:3000")  // Specify allowed origins
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // Allow all methods
                        .allowedHeaders("*");
//                        .allowCredentials(true);
            }
        };
    }
}
