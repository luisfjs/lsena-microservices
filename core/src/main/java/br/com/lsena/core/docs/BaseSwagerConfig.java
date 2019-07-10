package br.com.lsena.core.docs;

import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

public class BaseSwagerConfig {
    private final String basePackage;

    public BaseSwagerConfig(String basePackage) {
        this.basePackage = basePackage;
    }

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .build()
                .apiInfo(metaData());
    }
    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("Another Awesome course from DevDojo <3 Spring Boot Microservices")
                .description("Everbody is Jedi now")
                .version("1.0")
                .contact(new Contact("Luis Sena", "http://lsena.com.br", "lsena@lsena.com.br"))
                .license("Private stuf bro, belongs to LSena")
                .licenseUrl("http://lsena.com.br")
                .build();
    }
}
