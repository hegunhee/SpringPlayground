package hello.aop.pointcut;

import hello.aop.order.aop.member.MemberService;
import hello.aop.order.aop.member.annotation.ClassAop;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@SpringBootTest
@Import(ParameterTest.ParameterAspect.class)
public class ParameterTest {

    @Autowired
    MemberService memberService;

    @Test
    void success() {
        log.info("memberSerivce Proxy={}",memberService.getClass());
        memberService.hello("helloA");
    }

    @Slf4j
    @Aspect
    static class ParameterAspect {

        @Pointcut("execution(* hello.aop.order..*.*(..))")
        private void allMember() {}

        @Around("allMember()")
        public Object logArgs1(ProceedingJoinPoint joinPoint) throws Throwable {
            Object arg1 = joinPoint.getArgs()[0];
            log.info("[logArgs1]{}, arg={}",joinPoint.getSignature(),arg1);
            return joinPoint.proceed();
        }

        @Around("allMember() && args(arg,..)")
        public Object logArg2(ProceedingJoinPoint joinPoint,Object arg) throws Throwable {
            log.info("[logArgs2]{}, arg={}",joinPoint.getSignature(),arg);
            return joinPoint.proceed();
        }

        @Before("allMember() && args(arg,..)")
        public void logArg3(String arg) {
            log.info("[logArg3] arg={}",arg);
        }

        @Before("allMember() && this(obj)") // 감싸고있는 객체
        public void thisArgs(JoinPoint joinPoint, MemberService obj) {
            log.info("[this]{} obj={}",joinPoint.getSignature(),obj.getClass());
        }

        @Before("allMember() && target(obj)") //실제 대상을 해야한다
        public void targetArgs(JoinPoint joinPoint, MemberService obj) {
            log.info("[target]{} obj={}",joinPoint.getSignature(),obj.getClass());
        }

        @Before("allMember() && @target(annotation)") //실제 대상을 해야한다
        public void atTarget(JoinPoint joinPoint, ClassAop annotation) {
            log.info("[@target]{} obj={}",joinPoint.getSignature(),annotation.getClass());
        }
    }
}
