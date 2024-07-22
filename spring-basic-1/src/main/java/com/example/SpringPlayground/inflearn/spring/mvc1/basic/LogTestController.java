package com.example.SpringPlayground.inflearn.spring.mvc1.basic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j // 롬북이 제공하는 어노테이션 log를 쉽게 사용 가능
@RestController
public class LogTestController {

    @RequestMapping("/log-test") // RestController의 메서드는 반환된 String이 result로 보내짐
    private String logTest() {
        String name = "Spring";

        log.info(" info log={}",name); // 현재시간, 스레드정보, 현재 클래스등 좋은 정보들을 알 수 있음
        log.trace(" info log={}",name); // 해당 포맷에 맞게 사용 만약 중괄호를 사용한다면 연산은 다 하고 출력을 안함 즉 메모리 낭비
        log.debug(" info log={}",name);
        log.warn(" info log={}",name);
        log.error(" info log={}",name);
        // 여러가지로 설정 가능함
        // trace와 debug는 application.properties 파일로 우선순위를 설정할 수 있음
        // 특정 로그만 추출할 수 있으므로 sout보다는 log가 선호됨
        return "ok";
    }
}
