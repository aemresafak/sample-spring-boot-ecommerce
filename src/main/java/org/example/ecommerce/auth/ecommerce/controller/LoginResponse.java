package org.example.ecommerce.auth.ecommerce.controller;

import java.util.UUID;

public record LoginResponse(String accessToken, UUID refreshTokenId, String refreshToken) {
}
