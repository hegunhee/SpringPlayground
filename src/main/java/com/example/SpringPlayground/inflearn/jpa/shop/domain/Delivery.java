package com.example.SpringPlayground.inflearn.jpa.shop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Delivery {

    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery",fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING) // 중간에 다른 상태가 들어간다면 곤란하다 (DB 상태가 변경되므로)
    private DeliveryStatus status; // READY, COMP
}
