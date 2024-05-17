package com.example.SpringPlayground.inflearn.spring.basic.scan.filter;

import java.lang.annotation.*;

@Target(ElementType.TYPE) // 해당 어노테이션이 어디에 붙을지 (클래스 상단, 필드)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent {
}
