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

    @Bean
    public GroupedOpenApi memberApi() {
        return GroupedOpenApi.builder()
                .group("member")
                .packagesToScan("project.study_with_me.domain.member")
                .build();
    }

    @Bean
    public GroupedOpenApi studyApi() {
        return GroupedOpenApi.builder()
                .group("study")
                .packagesToScan("project.study_with_me.domain.study")
                .build();
    }

    @Bean
    public GroupedOpenApi s3Api() {
        return GroupedOpenApi.builder()
                .group("image")
                .packagesToScan("project.study_with_me.s3")
                .build();
    }
}
