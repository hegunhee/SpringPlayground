package com.example.SpringPlayground.inflearn.spring.basic.singleton;

public class StatefulService {
    
    public int order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        return price;
    }

}
