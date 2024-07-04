package com.forohub.forohub.infra.security;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfiguration {
    @Bean
    public OpenAPI customOpenAPI() {
        Contact myContact = new Contact();
        myContact.setName("German Gomez");
        myContact.setEmail("jg345762@gmail.com");

        Info information = new Info()
                .title("Foro Hub - Challenge - Back End")
                .version("1.0")
                .description("Esta API expone endpoins para gestionar topicos.")
                .contact(myContact);

        return new OpenAPI()
                .info(information)
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));
    }


}
