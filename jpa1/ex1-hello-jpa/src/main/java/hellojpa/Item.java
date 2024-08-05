package hellojpa;

import jakarta.persistence.*;

@Entity
public class Item {

    @Id @GeneratedValue
    private Long id;

    private String name;
    private int price;

}
