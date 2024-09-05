package hello.aop.pointcut;

import hello.aop.order.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import java.lang.reflect.Method;

@Slf4j
public class ExecutionTest {

    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    Method helloMethod;

    @BeforeEach
    public void init() throws NoSuchMethodException {
        helloMethod = MemberServiceImpl.class.getMethod("hello", String.class);
    }

    @Test
    void printMethod() {
        log.info("helloMethod={}",helloMethod);
    }

    @Test
    void exactMatch() {
        //public java.lang.String hello.aop.order.aop.member.MemberServiceImpl.hello(java.lang.String)
        pointcut.setExpression("execution(public String hello.aop.order.aop.member.MemberServiceImpl.hello(String))");
        Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }

    @Test
    void allMatch() {
        pointcut.setExpression("execution(* *(..))");
        Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }

    @Test
    void nameMatch() {
        pointcut.setExpression("execution(* hello(..))");
        Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }

    @Test
    void nameMatchStar1() {
        pointcut.setExpression("execution(* hel*(..))");
        Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }

    @Test
    void nameMatchStar2() {
        pointcut.setExpression("execution(* *l*(..))");
        Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }

    @Test
    void nameMatchFalse() {
        pointcut.setExpression("execution(* nono(..))");
        Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isFalse();
    }

    @Test
    void packageExactMatch1() {
        pointcut.setExpression("execution(* hello.aop.order.aop.member.MemberServiceImpl.hello(..))");
        Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }

    @Test
    void packageExactMatch2() {
        pointcut.setExpression("execution(* hello.aop.order.aop.member.*.*(..))");
        Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }

    @Test
    void packageExactFalse() {
        pointcut.setExpression("execution(* hello.aop.a.*.*(..))");
        Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isFalse();
    }

    @Test
    void packageMatchSubPackage1() {
        pointcut.setExpression("execution(* hello.aop..*.*(..))");
        Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }

    @Test
    void packageMatchSubPackage2() {
        pointcut.setExpression("execution(* hello.aop.order..*.*(..))");
        Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }

    @Test
    void typeExactMatch() {
        pointcut.setExpression("execution(* hello.aop.order.aop.member.MemberServiceImpl.*(..))");
        Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }

    @Test
    void typeMatchSuperType() {
        pointcut.setExpression("execution(* hello.aop.order.aop.member.MemberService.*(..))");
        Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }

    @Test
    void typeMatchSuperInternal() throws NoSuchMethodException {
        pointcut.setExpression("execution(* hello.aop.order.aop.member.MemberService.*(..))");
        Method internal = MemberServiceImpl.class.getMethod("internal", String.class); // 부모 타입에서 조회하기때문에 당연히 불가능
        Assertions.assertThat(pointcut.matches(internal,MemberServiceImpl.class)).isFalse();
    }

    @Test
    void typeMatchInternal() throws NoSuchMethodException {
        pointcut.setExpression("execution(* hello.aop.order.aop.member.MemberServiceImpl.*(..))");
        Method internal = MemberServiceImpl.class.getMethod("internal", String.class); // 부모 타입에서 조회하기때문에 당연히 불가능
        Assertions.assertThat(pointcut.matches(internal,MemberServiceImpl.class)).isTrue();
    }

    // String 타입의 파라미터 허용
    // (String)
    @Test
    void argsMatch() {
        pointcut.setExpression("execution(* *(String))");
        Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }

    // 파라미터가 없어야 함
    //()
    @Test
    void argsMatchNoArgs() {
        pointcut.setExpression("execution(* *())");
        Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isFalse();
    }

    //정확히 하나의 파라미터 허용, 모든 타입 허용
    //(Xxx)
    @Test
    void argsMatchStar() {
        pointcut.setExpression("execution(* *(*))");
        Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }

    //숫자와 무관하게 모든 파라미터, 모든 타입 허용
    //(), (Xxx), (Xxx, Xxx)
    @Test
    void argsMatchAll() {
        pointcut.setExpression("execution(* *(..)");
        Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }

    //String 타입으로 시작, 숫자와 무관하게 모든 파라미터, 모든 타입 허용
    //(String), (String,Xxx), (string, Xxx, Xxx)
    @Test
    void argsMatchComplex() {
        pointcut.setExpression("execution(* *(String,..))");
        Assertions.assertThat(pointcut.matches(helloMethod,MemberServiceImpl.class)).isTrue();
    }
}
