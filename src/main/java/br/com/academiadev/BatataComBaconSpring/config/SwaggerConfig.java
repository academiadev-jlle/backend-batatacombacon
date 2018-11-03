package br.com.academiadev.BatataComBaconSpring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket apis() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.academiadev.BatataComBaconSpring"))
                .paths(PathSelectors.any()).build()
                .apiInfo(apiInfo()
                ).useDefaultResponseMessages(false);
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "PETcodes",
                "Backend para site de localização de Pets",
                "1.0.0",
                "Termos de Serviço",
                null,
                "Licença Apache 2",
                "https://github.com/academiadev-jlle/backend-batatacombacon/blob/master/LICENSE.md",
                new ArrayList<>());
    }
}