package org.example.ecommerce.auth;

import java.util.UUID;

public record AuthTokens(String accessToken, UUID refreshTokenId, String refreshToken) {
}