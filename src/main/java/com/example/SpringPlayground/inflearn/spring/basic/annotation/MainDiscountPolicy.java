package com.example.SpringPlayground.inflearn.spring.basic.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Qualifier("mainDiscountPolicy") // 휴먼 에러를 줄이기 위해서 이렇게 지정함
public @interface MainDiscountPolicy {
}
