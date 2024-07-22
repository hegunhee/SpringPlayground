package com.example.SpringPlayground.inflearn.jpa.shop.domain.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@Getter
@DiscriminatorValue("B") // Single table의 구분자
public class Book extends Item {

    private String author;
    private String isBn;

    public static Book createBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        return book;
    }

    public static Book createBook(String name, int price, int stockQuantity,String author, String isBn) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        book.author = author;
        book.isBn = isBn;
        return book;
    }

}
