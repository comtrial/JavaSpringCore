package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    //hello.core.AppConfig 를 통해
    // 구현체에서 인터페이스에만 의존하는 것이 아닌 구현체에도 의존하는 문제점
    // 이로 인한 DIP 가 지켜지지 않음의 문제와 OCP(확장에는 열러있고 변경에는 닫혀 있어야 함 -> 역할에 의존, 즉 인터페이스에만 의존해야함) 원칙이 지켜지지 않은 문제
    // discount 정책 변화 등의 변화 대체 부분에 있어서의 구현체 소스 코드의 수정 과정을 해결 해 봄으로써 DI 의 필요성에 대한 숙지
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

//    @Bean
//    public OrderService orderService() {
//        return new OrderServiceImpl(memberRepository(), discountPolicy());
//    }
}
