package com.gaetanl.cv.service;

import com.gaetanl.cv.exception.DuplicateEntityException;
import com.gaetanl.cv.exception.EntityNotFoundException;
import com.gaetanl.cv.exception.IllegalEntityArgument;
import com.gaetanl.cv.model.Skill;
import com.gaetanl.cv.model.SkillType;
import com.gaetanl.cv.repository.SkillRepository;
import com.gaetanl.cv.repository.SkillTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkillService implements CvService<Skill> {
    @Autowired
    private SkillTypeRepository skillTypeRepository;
    @Autowired
    private SkillRepository skillRepository;

    public @NonNull Skill create (@NonNull final Skill skill) {
        if (skill.getId() != null) throw new IllegalEntityArgument(String.format("Cannot create %s with non null id", skill.getClass().getSimpleName()));

        final Optional<Skill> alreadyExistingSkill = skillRepository.findFirstByName(skill.getName());
        if (alreadyExistingSkill.isPresent()) throw new DuplicateEntityException(alreadyExistingSkill.get(), "name", skill.getName());

        return createOrUpdate(skill);
    }

    public @NonNull Optional<Skill> read(@NonNull final Long id) {
        if (id == null) throw new IllegalEntityArgument("id cannot be null");
        return skillRepository.findById(id);
    }

    public @NonNull Optional<Skill> readByName(@NonNull final String name) {
        return skillRepository.findFirstByName(name);
    }

    public @NonNull List<Skill> readAll() {
        return skillRepository.findAll();
    }

    public @NonNull Skill update(@NonNull final Skill skill) {
        final Optional<Skill> dbSkill = read(skill.getId());
        if (dbSkill.isEmpty()) throw new EntityNotFoundException(skill);
        skill.setId(dbSkill.get().getId());
        return createOrUpdate(skill);
    }

    public void delete(@NonNull final Skill skill) {
        if (skillRepository.findById(skill.getId()).isEmpty()) throw new EntityNotFoundException(skill);

        skillRepository.delete(skill);
    }

    private @NonNull Skill createOrUpdate(@NonNull final Skill skill) {
        if ((skill.getName() == null) || (skill.getName().isEmpty())) throw new IllegalEntityArgument(String.format("%s name cannot be empty", skill.getClass().getSimpleName()));
        if ((skill.getType() == null)) throw new IllegalEntityArgument(String.format("%s type cannot be empty", skill.getClass().getSimpleName()));

        final Optional<SkillType> dbSkillType = skillTypeRepository.findFirstByName(skill.getType().getName());
        if (dbSkillType.isEmpty())
            skill.setType(skillTypeRepository.save(skill.getType()));
        else
            skill.setType(dbSkillType.get());

        return skillRepository.save(skill);
    }
}
