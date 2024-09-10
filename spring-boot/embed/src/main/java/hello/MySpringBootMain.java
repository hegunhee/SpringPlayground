package hello;

import hello.boot.MySpringApplication;
import hello.boot.MySpringBootApplication;

@MySpringBootApplication // 컴포넌트 스캔을 사용하겠다
public class MySpringBootMain {

    public static void main(String[] args) {
        System.out.println("MySpringBootMain.main");
        MySpringApplication.run(MySpringBootMain.class,args);
    }
}
