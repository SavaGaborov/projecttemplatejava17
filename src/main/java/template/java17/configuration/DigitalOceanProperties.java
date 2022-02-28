package template.java17.configuration;

import lombok.Data;

@Data
public class DigitalOceanProperties {

    private String accessKey;

    private String secretKey;

    private String bucket;

    private String region;

    private String url;
}