package hello.core;

import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class AutoAppConfigTest {
    @Test
    void basicScan() {

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        MemberService memberServiceImpl = ac.getBean(MemberServiceImpl.class);
        OrderService orderServiceImpl = ac.getBean(OrderServiceImpl.class);

        System.out.println(memberServiceImpl);
        System.out.println(orderServiceImpl);

        Assertions.assertThat(memberServiceImpl).isInstanceOf(MemberServiceImpl.class);
        Assertions.assertThat(orderServiceImpl).isInstanceOf(OrderService.class);
    }
}