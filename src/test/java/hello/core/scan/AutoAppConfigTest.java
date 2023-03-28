package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import hello.core.scan.filter.BeanA;
import hello.core.scan.filter.BeanB;
import hello.core.scan.filter.MyExcludeComponent;
import hello.core.scan.filter.MyIncludeComponent;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.context.annotation.ComponentScan.*;

class AutoAppConfigTest {

    @Test
    void filterScan() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        BeanB beanB = ac.getBean("beanB", BeanB.class);

        Assertions.assertThat(beanB).isNotNull();
        org.junit.jupiter.api.Assertions.assertThrows(
                NoSuchBeanDefinitionException.class,
                () -> ac.getBean("beanA", BeanA.class)
        );
    }


    @Configuration
    @ComponentScan(
            includeFilters = @Filter(
                    type = FilterType.ANNOTATION, classes = MyIncludeComponent.class
            ),
            excludeFilters = @Filter(
                    type = FilterType.ANNOTATION, classes = MyExcludeComponent.class
            )
    )
    static class ComponentFilterAppConfig {
    }
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