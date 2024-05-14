package com.example.SpringPlayground.inflearn.spring.basic.domain.discount;

import com.example.SpringPlayground.inflearn.spring.basic.domain.member.Grade;
import com.example.SpringPlayground.inflearn.spring.basic.domain.member.Member;

public class RateDiscountPolicy implements DiscountPolicy{

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}
