package com.gaetanl.cv.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface CvEntity {
    Long getId();

    @JsonIgnore
    default String getIdAsString() {return String.valueOf(getId());}
}
