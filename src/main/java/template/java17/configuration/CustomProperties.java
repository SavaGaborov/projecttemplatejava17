package template.java17.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Data
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "custom", ignoreUnknownFields = false)
@Validated
public class CustomProperties {
}
