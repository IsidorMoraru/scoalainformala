package com.java.projectfinal.config;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Value("${application-description}")
    final String projectDescription = """
            Project Final 
            _________________
        A project final, for the informal school of IT..
            """;
    @Bean
    public OpenAPI customOpenApi(@Value("${application-version}") String appVersion) {
        return new OpenAPI()
                .info(new Info()
                        .title("Project Final API")
                        .version(appVersion)
                        .description(projectDescription)
                        .termsOfService(""));
    }

}






