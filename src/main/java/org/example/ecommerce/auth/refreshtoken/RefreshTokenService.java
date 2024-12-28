package org.example.ecommerce.auth.refreshtoken;

import lombok.RequiredArgsConstructor;
import org.example.ecommerce.auth.jwt.JWTService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class RefreshTokenService {
    private final RefreshTokenJpaService refreshTokenJpaService;
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public AuthenticationTokens refresh(UUID tokenId, String refreshToken) {
        return refreshTokenJpaService.fetchRefreshToken(tokenId)
                .filter(original -> doesInputRefreshTokenMatchActual(original.token(), refreshToken))
                .filter(this::isRefreshTokenValid)
                .map(this::handleValidRefreshToken)
                .orElseThrow(InvalidRefreshTokenException::new);
    }

    private boolean doesInputRefreshTokenMatchActual(String encodedRefreshToken, String input) {
        return passwordEncoder.matches(input, encodedRefreshToken);
    }

    private boolean isRefreshTokenValid(RefreshToken refreshToken) {
        var isUsed = refreshToken.deleted();
        var isExpired = refreshToken.expiresAt().isBefore(Instant.now());
        return !isUsed && !isExpired;
    }

    private AuthenticationTokens handleValidRefreshToken(RefreshToken refreshToken) {
        var member = refreshToken.member();
        var jwt = jwtService.generateToken(member);
        refreshTokenJpaService.deleteRefreshToken(refreshToken.id());
        var newRefreshToken = refreshTokenJpaService.createRefreshToken(member.id(), refreshToken.expiresAt());
        return new AuthenticationTokens(jwt, newRefreshToken.id(), newRefreshToken.token());
    }
}
