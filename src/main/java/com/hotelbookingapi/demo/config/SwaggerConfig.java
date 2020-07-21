package com.hotelbookingapi.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hotelbookingapi.demo.controller"))
                .build()
                .apiInfo(metaData());
    }

	private ApiInfo metaData() {
		ApiInfo apiInfo = new ApiInfo("Hotel booking API", "This API is used to book hotel rooms", "V1", "terms",
				"Sachin", "demo license", "https://demolicenseurl.com");
		return apiInfo;
	}
}
