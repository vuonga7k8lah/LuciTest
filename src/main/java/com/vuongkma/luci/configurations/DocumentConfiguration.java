package com.vuongkma.luci.configurations;


import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DocumentConfiguration {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Luci Test CRUD API")
                        .description("TEST")
                        .license(new License().name("Test").url("https://luci/")))
                .externalDocs(new ExternalDocumentation()
                        .description("Luci Test Documentation")
                        .url("https://springshop.wiki.github.org/docs"));
    }
}
