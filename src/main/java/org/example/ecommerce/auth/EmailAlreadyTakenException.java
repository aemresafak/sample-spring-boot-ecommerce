package org.example.ecommerce.auth;

import org.example.ecommerce.common.BusinessException;

public class EmailAlreadyTakenException extends BusinessException {
    public EmailAlreadyTakenException(String email) {
        super(email + " is already taken.");
    }
}
