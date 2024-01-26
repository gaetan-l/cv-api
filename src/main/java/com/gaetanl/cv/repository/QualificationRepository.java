package com.gaetanl.cv.repository;

import com.gaetanl.cv.model.Qualification;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QualificationRepository extends ListCrudRepository<Qualification, Long> {
}
