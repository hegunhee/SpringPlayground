package hello.aop.pointcut;

import hello.aop.order.aop.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

public class ArgsTest {

    Method helloMethod;

    @BeforeEach
    public void init() throws NoSuchMethodException {
        helloMethod = MemberServiceImpl.class.getMethod("hello", String.class);
    }

    private AspectJExpressionPointcut pointcut(String expression) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(expression);
        return pointcut;
    }

    @Test
    void args() {
        assertThat(pointcut("args(String)")
                .matches(helloMethod,MemberServiceImpl.class)).isTrue();
        assertThat(pointcut("args(Object)")
                .matches(helloMethod,MemberServiceImpl.class)).isTrue();
        assertThat(pointcut("args(String,..)")
                .matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }

    /**
     * execution(* *(java.io.serializable)); 메서드의 시그니처로 판단 (정적) -> 정확하게 매칭해야함
     * args(*); 동적 -> 상위 타입도 가능
     */
}
