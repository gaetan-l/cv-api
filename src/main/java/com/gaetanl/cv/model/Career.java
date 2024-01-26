package com.gaetanl.cv.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Career implements CvEntity {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique=true)
    private String name;
}
