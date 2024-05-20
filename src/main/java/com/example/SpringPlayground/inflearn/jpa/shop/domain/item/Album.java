package com.example.SpringPlayground.inflearn.jpa.shop.domain.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@DiscriminatorValue("A") // Single table의 구분자
public class Album extends Item {

    private String artist;
    private String etc;
}
