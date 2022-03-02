package template.java17.configuration;

import java.security.Principal;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@Configuration
//@EnableSwagger2
public class SwaggerConfiguration {

//    @Value("${custom.swagger-url}")
//    private String swaggerUrl;
//
//    private static final Parameter AUTH_HEADER = new ParameterBuilder().name("Authorization")
//            .description("Authentication token required for everything except AuthenticationController")
//            .modelRef(new ModelRef("string"))
//            .parameterType("header")
//            .required(false)
//            .build();
//
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .host("localhost:8080")
//                .ignoredParameterTypes(Principal.class)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("checkback.app.web"))
//                .paths(PathSelectors.regex("/.*"))
//                .build()
//                .globalOperationParameters(Collections.singletonList(AUTH_HEADER));
//    }
}