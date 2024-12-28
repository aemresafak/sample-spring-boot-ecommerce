package org.example.ecommerce.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class RandomStringUtils {
    private static final String ALLOWED_CHARS = "0123456789abcdefghijklmnopqrstuvwxyz-_ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final SecureRandom secureRandom;

    static {
        SecureRandom temp;
        try {
            temp = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            log.error("Strong Secure Random could not be initialized", e);
            temp = new SecureRandom();
        }
        secureRandom = temp;
    }

    public static String randomAlphaNumeric(int size) {
        return secureRandom.ints(size, 0, ALLOWED_CHARS.length())
                .mapToObj(ALLOWED_CHARS::charAt)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }
}
