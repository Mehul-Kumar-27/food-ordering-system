package com.food.ordering.system.domain.exception;

import com.food.ordering.system.exceptions.DomainExceptions;

public class OrderDomainException extends DomainExceptions {
    public OrderDomainException(String message) {
        super(message);
    }

    public OrderDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
