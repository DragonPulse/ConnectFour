package com.ninty8point6.connectfour.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;


/**
 * The type Swagger config.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Configuration for Swagger API.
     *
     * @return docket docket
     */
    @Bean
    public Docket scrabbleApi() {
        logger.info("Entering into COnfig ::");
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ninty8point6.connectfour.controllers"))
                .paths(regex("/.*"))
                .build()
                .apiInfo(metaData());
    }

    /**
     * Meta Data defination which will be displayed in Swagger API
     *
     * @return
     */
    private ApiInfo metaData() {
        logger.info("Entering into MetaData ::");

        io.swagger.models.Contact contact = new io.swagger.models.Contact();
        ApiInfo apiInfo = new ApiInfo(
                "Connect Four Game",
                "Connect Four Game Designed and Developed by VJ ",
                "1.0",
                "Terms of service",
                " Vijay Kumar -ait@gmail.com",
                "",
                "https://www.apache.org/licenses/LICENSE-2.0");
        return apiInfo;
    }
}

