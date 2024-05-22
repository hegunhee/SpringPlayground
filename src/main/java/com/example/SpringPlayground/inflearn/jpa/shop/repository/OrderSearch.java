package com.example.SpringPlayground.inflearn.jpa.shop.repository;

import com.example.SpringPlayground.inflearn.jpa.shop.domain.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderSearch {

    private String memberName; // 회원 이름
    private OrderStatus orderStatus; // 주문 상태(ORDER, CANCELED)
}
