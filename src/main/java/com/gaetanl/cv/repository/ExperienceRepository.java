package com.gaetanl.cv.repository;

import com.gaetanl.cv.model.Experience;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienceRepository extends ListCrudRepository<Experience, Long> {
}
