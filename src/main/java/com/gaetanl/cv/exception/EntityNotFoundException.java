package com.gaetanl.cv.exception;

import com.gaetanl.cv.model.CvEntity;

public class EntityNotFoundException extends EntityException {
    public EntityNotFoundException(final CvEntity entity) {
        super(String.format("%s with id=%d not found",
                entity.getClass().getSimpleName(),
                entity.getId()));
    }
}
