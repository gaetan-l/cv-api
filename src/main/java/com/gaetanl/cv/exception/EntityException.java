package com.gaetanl.cv.exception;

public abstract class EntityException extends IllegalArgumentException {
    EntityException(String message) {
        super(message);
    }
}
