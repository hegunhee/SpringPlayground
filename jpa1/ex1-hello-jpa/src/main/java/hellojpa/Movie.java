package hellojpa;

import jakarta.persistence.Entity;

@Entity
//@DiscriminatorValue로 DTYPE의 값을 변경 ㄱ능함
public class Movie extends Item{

    private String director;
    private String actor;
}
