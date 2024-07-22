package com.example.SpringPlayground.inflearn.spring.mvcweb.itemservice.domain.item;

import lombok.Data;

@Data // @Data는 좀 주의해서 사용해야함 Data 어노테이션의 내용을 파악해야함
public class Item {

    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;

    public Item() {

    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
