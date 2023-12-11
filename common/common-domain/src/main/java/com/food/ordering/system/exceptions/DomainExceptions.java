package com.food.ordering.system.exceptions;

public class DomainExceptions extends RuntimeException {
    public DomainExceptions(String message) {
        super(message);
    }

    public DomainExceptions(String message, Throwable cause) {
        super(message, cause);
    }
}