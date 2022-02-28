package template.java17.configuration;

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