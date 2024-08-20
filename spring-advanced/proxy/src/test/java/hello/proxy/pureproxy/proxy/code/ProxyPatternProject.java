package hello.proxy.pureproxy.proxy.code;

public class ProxyPatternProject {

    private Subject subject;

    public ProxyPatternProject(Subject subject) {
        this.subject = subject;
    }

    public void execute() {
        subject.operation();
    }
}
