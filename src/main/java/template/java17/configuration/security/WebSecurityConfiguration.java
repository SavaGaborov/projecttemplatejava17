package template.java17.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

//@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration {

//    @Autowired
//    public WebSecurityConfiguration(PathMatcher pathMatcher, TokenEncoder tokenEncoder, CustomProperties customProperties, RestAuthenticationEntryPoint restAuthenticationEntryPoint) {
//        this.pathMatcher = pathMatcher;
//        this.tokenEncoder = tokenEncoder;
//        this.customProperties = customProperties;
//        this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
//    }
//
    protected static final Set<String> UNSECURED_APIS = new HashSet<String>(6) {{
        add("/health");
        add("/h2-console/");
        add("/swagger-ui.html");
        add("/swagger-resources/");
        add("/v2/");
        add("/webjars/");
    }};
//
//    private final PathMatcher pathMatcher;
//    private final TokenEncoder tokenEncoder;
//    private final CustomProperties customProperties;
//    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;
//
//    @Autowired
//    public WebSecurityConfiguration(PathMatcher pathMatcher, TokenEncoder tokenEncoder, ObjectMapper objectMapper,
//                                    CustomProperties customProperties) {
//        this.pathMatcher = pathMatcher;
//        this.tokenEncoder = tokenEncoder;
//        this.customProperties = customProperties;
//        this.restAuthenticationEntryPoint =
//                new RestAuthenticationEntryPoint("customProperties.getServerUrl()", objectMapper);
//    }
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http.csrf()
//                .disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and().authorizeRequests()
//                .antMatchers("/swagger-ui.html", "/swagger-resources/**", "/v2/**", "/webjars/**").permitAll()
//                .and()
//                .exceptionHandling()
//                .authenticationEntryPoint(restAuthenticationEntryPoint)
//                .accessDeniedHandler(restAuthenticationEntryPoint);
//        http.headers().frameOptions().disable();
//    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.singletonList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

//    @Bean
//    public FilterRegistrationBean<JwtRequestFilter> jwtRequestFilterFilterRegistrationBean() {
//        FilterRegistrationBean<JwtRequestFilter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(
//                new JwtRequestFilter(restAuthenticationEntryPoint, pathMatcher, tokenEncoder, userRepository));
//        registrationBean.addUrlPatterns("/api/*");
//        registrationBean.setOrder(1);
//        return registrationBean;
//    }

//    @Bean
//    public FilterRegistrationBean<ApiKeyRequestFilter> apiKeyRequestFilterFilterRegistrationBean() {
//        FilterRegistrationBean<ApiKeyRequestFilter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean
//                .setFilter(new ApiKeyRequestFilter(pathMatcher, customProperties, restAuthenticationEntryPoint));
//        registrationBean.addUrlPatterns("/chat/*");
//        registrationBean.setOrder(2);
//        return registrationBean;
//    }
//
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
}
