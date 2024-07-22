package hellojpa;

import jakarta.persistence.Entity;

@Entity
//@DiscriminatorValue로 DTYPE의 값을 변경 ㄱ능함
public class Book extends Item{

    private String author;
    private String isbn;
}
