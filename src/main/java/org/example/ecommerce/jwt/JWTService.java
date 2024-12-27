package org.example.ecommerce.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.ecommerce.common.BusinessException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collection;

import static net.logstash.logback.argument.StructuredArguments.kv;
import static org.example.ecommerce.jwt.JWTConstants.CLAIM_AUTHORITIES;

@Slf4j
@Service
@RequiredArgsConstructor
public class JWTService {

    private final JWTProperties jwtProperties;

    public String generateToken(String principal, Collection<String> roles) {
        log.info("Generating JWT token for {}", kv("principal", principal));
        try {
            var algorithm = jwtProperties.getAlgorithm();
            return JWT.create().withExpiresAt(Instant.now().plus(jwtProperties.getAccessTokenValidity())).withArrayClaim(CLAIM_AUTHORITIES, roles.toArray(new String[0])).withSubject(principal).sign(algorithm);
        } catch (JWTCreationException exception) {
            log.error("JWT token could not be created {}", kv("principal", principal));
            throw new BusinessException();
        }
    }

    public DecodedJWT validateToken(String token) {
        Algorithm algorithm = jwtProperties.getAlgorithm();
        JWTVerifier verifier = JWT.require(algorithm).withClaimPresence(CLAIM_AUTHORITIES).withClaimPresence("sub").build();

        return verifier.verify(token);
    }


}
