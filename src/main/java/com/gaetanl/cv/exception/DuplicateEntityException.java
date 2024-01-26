package com.gaetanl.cv.exception;

import com.gaetanl.cv.model.CvEntity;

public class DuplicateEntityException extends EntityException {
    public DuplicateEntityException(CvEntity entity, String attributeName, String attributeValue) {
        super(String.format("%s with %s=\"%s\" already exists with id=%d",
                entity.getClass().getSimpleName(),
                attributeName,
                attributeValue,
                entity.getId()));
    }
}
