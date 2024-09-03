package hello.proxy.app.v2;

public class OrderRepositoryV2 {

    public void save(String itemId) {
        if(itemId.equals("ex")) {
            throw new IllegalStateException("exception 발생");
        }
        sleep(1000);
    }

    private void sleep(int mills) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}