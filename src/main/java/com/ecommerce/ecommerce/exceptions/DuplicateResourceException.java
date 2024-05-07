package com.ecommerce.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.ALREADY_REPORTED)
public class DuplicateResourceException extends RuntimeException {
    public DuplicateResourceException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s already exists in DB with %s : %s", resourceName, fieldName, fieldValue));
    }
}
