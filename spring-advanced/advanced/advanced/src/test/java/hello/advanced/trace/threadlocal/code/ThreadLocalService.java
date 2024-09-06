package hello.advanced.trace.threadlocal.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadLocalService {

    private ThreadLocal<String> nameStore = new ThreadLocal<>();

    public String logic(String name) {
        log.info("저장 name={} -> nameStore={}",name,nameStore.get()); // 현재 쓰레드를 조회해서 ThreadMap에 저장한다.
        nameStore.set(name);
        sleep(1000);
        log.info("조회 nameStore={}",nameStore.get()); // 꺼낼때도 현재 쓰레드를 조회해서 값을 꺼낸다
        return nameStore.get();
    }

    private void sleep(int mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
