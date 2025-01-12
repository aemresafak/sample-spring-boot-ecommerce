package org.example.ecommerce.auth.refreshtoken;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.example.ecommerce.common.RandomStringUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class RefreshTokenUtils {
    private static final int REFRESH_TOKEN_LENGTH = 256;


    public static String generateRefreshToken() {
        return RandomStringUtils.randomAlphaNumeric(REFRESH_TOKEN_LENGTH);
    }
}
