package org.example.ecommerce.common;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        this("Requested resource is not found.");
    }
    public NotFoundException(String message) {
        super(message);
    }
}
