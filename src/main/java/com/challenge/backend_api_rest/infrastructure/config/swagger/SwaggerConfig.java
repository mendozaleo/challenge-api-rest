package com.challenge.backend_api_rest.infrastructure.config.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

        @Value("${spring.application.name:Calculadora API REST}")
        private String applicationName;

        @Bean
        public OpenAPI customOpenAPI() {
                return new OpenAPI()
                                .info(new Info()
                                                .title(applicationName)
                                                .description("API REST de Calculadora con arquitectura Domain-Driven Design")
                                                .version("1.0.0")
                                                .contact(new Contact()
                                                                .name("Challenge Backend API REST")
                                                                .email("contacto@challenge.com")
                                                                .url("https://github.com/challenge-backend-api-rest"))
                                                .license(new License()
                                                                .name("Apache 2.0")
                                                                .url("http://www.apache.org/licenses/LICENSE-2.0.html")))
                                .servers(List.of(
                                                
                                                new Server().url("http://localhost:8085")
                                                                .description("Servidor de Desarrollo")));
        }
}