package com.gaetanl.cv.repository;

import com.gaetanl.cv.model.Career;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CareerRepository extends ListCrudRepository<Career, Long> {
}
