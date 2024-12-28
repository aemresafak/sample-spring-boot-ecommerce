package org.example.ecommerce.auth.refreshtoken;

import java.util.UUID;

public record AuthenticationTokens(String accessToken, UUID refreshTokenId, String refreshToken) {
}
