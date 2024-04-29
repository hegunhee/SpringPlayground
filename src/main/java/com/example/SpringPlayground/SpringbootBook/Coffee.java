package com.example.SpringPlayground.SpringbootBook;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
class Coffee{
    @Id
    private String id;
    private String name;

    /*
     * Entity Class는 빈 생성자가 반드시 필요함
     */
    public Coffee() {

    }
    public Coffee(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Coffee(String id) {
        this(UUID.randomUUID().toString(),id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}