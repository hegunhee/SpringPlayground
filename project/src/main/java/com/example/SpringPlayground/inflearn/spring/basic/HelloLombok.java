package com.example.SpringPlayground.inflearn.spring.basic;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HelloLombok {


    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("asasd");

        System.out.println("helloLombok = " + helloLombok);
    }
}
