package hello.proxy.app.config.v2_proxy;

import hello.proxy.app.config.v2_proxy.concrete_proxy.OrderControllerConcreteProxy;
import hello.proxy.app.config.v2_proxy.concrete_proxy.OrderRepositoryConcreteProxy;
import hello.proxy.app.config.v2_proxy.concrete_proxy.OrderServiceConcreteProxy;
import hello.proxy.app.v2.OrderControllerV2;
import hello.proxy.app.v2.OrderRepositoryV2;
import hello.proxy.app.v2.OrderServiceV2;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConcreteProxyConfig {

    @Bean
    public OrderControllerV2 orderControllerV2(LogTrace logTrace) {
        OrderControllerV2 orderController = new OrderControllerV2(orderServiceV2(logTrace));
        return new OrderControllerConcreteProxy(orderController,logTrace);
    }

    @Bean
    public OrderServiceV2 orderServiceV2(LogTrace logTrace) {
        OrderServiceV2 serviceImpl = new OrderServiceV2(orderRepositoryV2(logTrace));
        return new OrderServiceConcreteProxy(serviceImpl,logTrace);

    }
    @Bean
    public OrderRepositoryV2 orderRepositoryV2(LogTrace logTrace) {
        OrderRepositoryV2 repository = new OrderRepositoryV2();
        return new OrderRepositoryConcreteProxy(repository,logTrace);
    }
}
