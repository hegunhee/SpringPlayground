package com.example.SpringPlayground.inflearn.jpa.shop.service;

import com.example.SpringPlayground.inflearn.jpa.shop.domain.item.Book;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ItemUpdateTest {

    @Autowired
    EntityManager em;
    @Test
    public void update() throws Exception {
        Book book = em.find(Book.class, 1L);
        //given
        book.setName("asdasd"); // 자동으로 JPA가 변경을 감지해서 DB 업데이트(dirty checking)

        //when

        //then
    }
}
