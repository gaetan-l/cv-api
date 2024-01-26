package com.gaetanl.cv.service;

import com.gaetanl.cv.model.CvEntity;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface CvService<T extends CvEntity> {
    @NonNull T create(@NonNull final T t);
    @NonNull Optional<T> read(@NonNull final Long id);
    @NonNull Optional<T> readByName(@NonNull final String name);
    @NonNull List<T> readAll();
    @NonNull T update(@NonNull final T t);
    void delete(@NonNull final T t);
}
