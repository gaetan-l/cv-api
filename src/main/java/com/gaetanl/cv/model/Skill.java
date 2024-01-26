package com.gaetanl.cv.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Skill implements CvEntity {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique=true)
    private String name;

    @ManyToOne
    private SkillType type;

    @ManyToMany(mappedBy = "skills")
    private List<Experience> xps = new ArrayList<>();

    @ManyToMany(mappedBy = "skills")
    private List<Qualification> qualifs = new ArrayList<>();
}
