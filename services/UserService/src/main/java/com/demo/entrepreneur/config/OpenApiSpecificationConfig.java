package com.demo.entrepreneur.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiSpecificationConfig {
    @Bean
    public OpenAPI customOpenAPI(@Value("${entrepreneur.version}") String appVersion) {
        return new OpenAPI()
//                .components(new Components().addSecuritySchemes("basicScheme",
//                        new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic")))
                .info(new Info().title("Enterpreneur API").version(appVersion).description(
                        "Application for convenient income calculation"));
//                        .termsOfService("http://swagger.io/terms/")
//                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}
