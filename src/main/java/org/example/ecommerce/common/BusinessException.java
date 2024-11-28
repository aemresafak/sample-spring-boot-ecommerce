package org.example.ecommerce.common;

public class BusinessException extends RuntimeException {
    public BusinessException() {
        super("Unknown error occurred");
    }
    public BusinessException(String message) {
        super(message);
    }
}
