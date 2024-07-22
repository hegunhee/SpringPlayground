package com.example.SpringPlayground.inflearn.jpa.shop.domain.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@DiscriminatorValue("M") // Single table의 구분자
public class Movie extends Item {

    private String director;
    private String actor;
}
