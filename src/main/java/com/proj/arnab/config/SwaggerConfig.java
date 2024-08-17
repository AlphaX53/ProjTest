package main.java.com.proj.arnab.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI stockQuoteServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Stock Quote Service API")
                        .description("Spring Boot REST API for retrieving stock quotes")
                        .version("v1.0.0"));
    }
}