package org.example.ecommerce.jwt;

import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.Nullable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JWTService jwtService;
    private final HandlerExceptionResolver handlerExceptionResolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        var authenticationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        var accessToken = getAccessTokenFromAuthenticationHeader(authenticationHeader);
        if (accessToken.isPresent()) {
            try {
                log.info("Validating token.");
                var jwt = jwtService.validateToken(accessToken.get());
                var authentication = DecodedJWTMapper.toAuthentication(jwt);

                SecurityContextHolder.clearContext();
                var securityContext = SecurityContextHolder.createEmptyContext();
                securityContext.setAuthentication(authentication);
                SecurityContextHolder.setContext(securityContext);
            } catch (JWTVerificationException e) {
                handlerExceptionResolver.resolveException(request,response, null, e);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private Optional<String> getAccessTokenFromAuthenticationHeader(@Nullable String headerValue) {
        if (headerValue == null || !headerValue.startsWith("Bearer ") || headerValue.length() < 8)
            return Optional.empty();
        return Optional.of(headerValue.substring(7));
    }
}
