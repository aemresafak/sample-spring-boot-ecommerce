package org.example.ecommerce.jwt;

import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;

import static org.example.ecommerce.jwt.JWTConstants.CLAIM_AUTHORITIES;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DecodedJWTMapper {

    public static Authentication toAuthentication(DecodedJWT jwt) {
        var principal = jwt.getSubject();
        var authorities = Arrays.stream(jwt.getClaim(CLAIM_AUTHORITIES).asArray(String.class)).map(SimpleGrantedAuthority::new).toList();

        return new UsernamePasswordAuthenticationToken(principal, null, authorities);
    }
}
