package com.gaetanl.cv.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "xp")
@Getter
@Setter
@NoArgsConstructor
public class Experience implements CvEntity {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique=true)
    private String name;

    @ManyToOne
    private Career career;

    @Enumerated(EnumType.STRING)
    private XpType type;
    public enum XpType {INTERNSHIP, JOB, SCHOOL, TRAINING}

    private LocalDate start;
    private LocalDate end;
    public Long getDuration() {
        return (end == null) ? null : ChronoUnit.MONTHS.between(YearMonth.from(start), YearMonth.from(end));
    }

    /*
     * Relationship between two experiences, e.g. an internship may be related
     * to a school course.
     * TODO: Specify rules to authorize or not a relationship.
     */
    @ManyToOne
    @JoinColumn(name = "related_xp_id")
    private Experience relatedExperience;
    private String purveyor;
    private String location;

    private String description;

    @ManyToMany
    @JoinTable(
            name = "xp_skill",
            joinColumns = @JoinColumn(name = "xp_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private List<Skill> skills = new ArrayList<>();
}
