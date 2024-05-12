package com.example.SpringPlayground.inflearn.spring.basic.service;

import com.example.SpringPlayground.inflearn.spring.basic.domain.order.Order;

public interface OrderService {
    Order createOrder(Long memberId,String itemName, int itemPrice);
}
