package template.java17.configuration.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.stereotype.Component;
import org.springframework.util.PathMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import template.java17.component.TokenEncoder;
import template.java17.configuration.CustomProperties;
import template.java17.repository.UserRepository;
import template.java17.web.rest.exception.RestAuthenticationEntryPoint;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
@Component
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    protected static final Set<String> UNSECURED_APIS = new HashSet<String>(6) {{
        add("/health");
        add("/h2-console/");
        add("/swagger-ui.html");
        add("/swagger-resources/");
        add("/v2/");
        add("/webjars/");
    }};

    private final PathMatcher pathMatcher;
    private final TokenEncoder tokenEncoder;
    private final UserRepository userRepository;
    private final CustomProperties customProperties;
    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;


    @Autowired
    public WebSecurityConfiguration(PathMatcher pathMatcher, TokenEncoder tokenEncoder,
                                    UserRepository userRepository, ObjectMapper objectMapper,
                                    CustomProperties customProperties) {
        this.pathMatcher = pathMatcher;
        this.tokenEncoder = tokenEncoder;
        this.userRepository = userRepository;
        this.customProperties = customProperties;
        this.restAuthenticationEntryPoint =
                new RestAuthenticationEntryPoint(customProperties.getServerUrl(), objectMapper);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests()
                .antMatchers("/swagger-ui.html", "/swagger-resources/**", "/v2/**", "/webjars/**").permitAll()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(restAuthenticationEntryPoint)
                .accessDeniedHandler(restAuthenticationEntryPoint);
        http.headers().frameOptions().disable();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.singletonList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public FilterRegistrationBean<JwtRequestFilter> jwtRequestFilterFilterRegistrationBean() {
        FilterRegistrationBean<JwtRequestFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(
                new JwtRequestFilter(restAuthenticationEntryPoint, pathMatcher, tokenEncoder, userRepository));
        registrationBean.addUrlPatterns("/api/*");
        registrationBean.setOrder(1);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<ApiKeyRequestFilter> apiKeyRequestFilterFilterRegistrationBean() {
        FilterRegistrationBean<ApiKeyRequestFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean
                .setFilter(new ApiKeyRequestFilter(pathMatcher, customProperties, restAuthenticationEntryPoint));
        registrationBean.addUrlPatterns("/chat/*");
        registrationBean.setOrder(2);
        return registrationBean;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
