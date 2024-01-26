package com.gaetanl.cv.repository;

import com.gaetanl.cv.model.Skill;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SkillRepository extends ListCrudRepository<Skill, Long> {
    Optional<Skill> findFirstByName(String name);
}
