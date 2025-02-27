package com.code.code_be_sub.global.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "Code Be Subject Swagger Docs",
                description = "Code 백엔드 과제 API 명세서",
                version = "v1.0.0")
)
@Configuration
public class SwaggerConfig {
}
