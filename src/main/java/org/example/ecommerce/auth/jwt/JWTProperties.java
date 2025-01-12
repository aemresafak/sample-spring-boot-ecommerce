package org.example.ecommerce.auth.jwt;

import com.auth0.jwt.algorithms.Algorithm;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "jwt")
class JWTProperties {
    private Duration accessTokenValidity;
    private String secret;

    public Algorithm getAlgorithm() {
        return Algorithm.HMAC256(secret);
    }
}
