package com.gaetanl.cv.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "qualif")
@Getter
@Setter
@NoArgsConstructor
public class Qualification implements CvEntity {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique=true)
    private String name;

    @ManyToOne
    private Career career;

    @Enumerated(EnumType.STRING)
    private QualificationType type;
    public enum QualificationType {DIPLOMA, CERTIFICATION}

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "related_xp_id")
    private Experience relatedExperience;
    private String purveyor;
    private String location;

    private String description;

    @ManyToMany
    @JoinTable(
            name = "qualif_skill",
            joinColumns = @JoinColumn(name = "qualif_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private List<Skill> skills = new ArrayList<>();
}
