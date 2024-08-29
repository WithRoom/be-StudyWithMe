package project.study_with_me.swagger.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi loginApi() {
        return GroupedOpenApi.builder()
                .group("Login")
                .packagesToScan("project.study_with_me.auth")
                .build();
    }
}
