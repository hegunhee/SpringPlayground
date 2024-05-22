package com.example.SpringPlayground.inflearn.jpa.shop.service;

import com.example.SpringPlayground.inflearn.jpa.shop.domain.Delivery;
import com.example.SpringPlayground.inflearn.jpa.shop.domain.Member;
import com.example.SpringPlayground.inflearn.jpa.shop.domain.Order;
import com.example.SpringPlayground.inflearn.jpa.shop.domain.OrderItem;
import com.example.SpringPlayground.inflearn.jpa.shop.domain.item.Item;
import com.example.SpringPlayground.inflearn.jpa.shop.repository.ItemRepository;
import com.example.SpringPlayground.inflearn.jpa.shop.repository.MemberRepository;
import com.example.SpringPlayground.inflearn.jpa.shop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    /**
     * 주문
     */
    @Transactional
    public Long order(Long memberId,Long itemId,int count) {
        //엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        //배송정보 조회
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        //주문상품 생성
        //cascadeType.ALL 때문에 Order를 persist하면 OrderItem도 persist하게 됨
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        //주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        //주문 저장
        orderRepository.save(order);
        return order.getId();
    }

    /**
     * 주문 취소
     * dirty check를 통해 내부값들이 변경되면 JPA가 DB값을 자동으로 변경해줌
     */
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findOne(orderId);
        order.cancel();
        // 도메인 모델 패턴 (단순히 서비스계층은 엔티티에 필요한 요청을 위임하는 역할
        // 엔티티가 비즈니스 로직을 가지고 객체 지향의 특징을 이용하는것
    }

//    public List<Order> findOrders(OrderSearch orderSearch) { return orderRepository.findAll(orderSearch)}

}
