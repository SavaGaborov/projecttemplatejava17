package template.java17.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Data
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "custom", ignoreUnknownFields = false)
@Validated
public class CustomProperties {

    private String env;

    private boolean test;

    private String supportMail;

    private String swaggerUrl;

    private String serverUrl;

    private String frontendUrl;

    private String website;

    private String jwtSigningKey;

    private long jwtValidity;

    private String jwtAudience;

    private String salt;

    @NotNull
    private DigitalOceanProperties digitalOcean;
}
