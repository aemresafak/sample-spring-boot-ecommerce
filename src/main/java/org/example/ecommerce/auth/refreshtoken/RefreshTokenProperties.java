package org.example.ecommerce.auth.refreshtoken;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.time.Duration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "refresh-token")
public class RefreshTokenProperties {
    private Duration validity;
}
