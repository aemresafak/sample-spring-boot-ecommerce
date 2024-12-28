package org.example.ecommerce.auth.controller;

import java.util.UUID;

public record LoginResponse(String accessToken, UUID refreshTokenId, String refreshToken) {
}
