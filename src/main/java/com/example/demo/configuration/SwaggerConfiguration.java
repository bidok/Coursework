package com.example.demo.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : bidok
 * @created : 31.03.2021, среда
 * @className : SwaggerConfiguration
 **/
@Configuration
public class SwaggerConfiguration {
    //@Bean
    public OpenAPI taxiOfficeOpenAPI(){

        return new OpenAPI()
                .info(
                    new Info()
                        .title("Coursework")
                        .version("1.0")
                        .contact(new Contact()
                            .email("bidokfeed@gmail.com")
                            .name("Vasia Bidyak")
                            .url("google.com")
                        )
                );

    }
}
