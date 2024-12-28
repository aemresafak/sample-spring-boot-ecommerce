package org.example.ecommerce.auth.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.ecommerce.common.BusinessException;
import org.example.ecommerce.member.Member;
import org.springframework.stereotype.Service;

import java.time.Instant;

import static net.logstash.logback.argument.StructuredArguments.kv;
import static org.example.ecommerce.auth.jwt.JWTConstants.CLAIM_AUTHORITIES;

@Slf4j
@Service
@RequiredArgsConstructor
public class JWTService {

    private final JWTProperties jwtProperties;

    public String generateToken(Member member) {
        log.info("Generating JWT token for {}", kv("principal", member.email()));
        try {
            var algorithm = jwtProperties.getAlgorithm();
            return JWT.create()
                    .withSubject(member.id().toString())
                    .withExpiresAt(Instant.now().plus(jwtProperties.getAccessTokenValidity()))
                    .withArrayClaim(CLAIM_AUTHORITIES, member.roles().toArray(new String[0]))
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            log.error("JWT token could not be created {}", kv("principal", member.email()));
            throw new BusinessException();
        }
    }

    public DecodedJWT validateToken(String token) {
        Algorithm algorithm = jwtProperties.getAlgorithm();
        JWTVerifier verifier = JWT.require(algorithm).withClaimPresence(CLAIM_AUTHORITIES).withClaimPresence("sub").build();

        return verifier.verify(token);
    }


}
