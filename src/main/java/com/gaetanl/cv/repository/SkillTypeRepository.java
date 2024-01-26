package com.gaetanl.cv.repository;

import com.gaetanl.cv.model.SkillType;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SkillTypeRepository extends ListCrudRepository<SkillType, Long> {
    Optional<SkillType> findFirstByName(String name);
}
