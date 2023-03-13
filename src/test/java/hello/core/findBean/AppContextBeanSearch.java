package hello.core.findBean;

import hello.core.AppConfig;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class AppContextBeanSearch {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("등록된 Bean 조희")
    void findBeanBy() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                System.out.println("beanDefinition = " + beanDefinitionName);
            }
        }
    }
    @Test
    @DisplayName("등록된 특정 Bean 조희")
    void findBeanSpecificType() {

        Object bean1 = ac.getBean(MemberServiceImpl.class);
        Object bean2 = ac.getBean(MemberService.class);

        assertThat(bean1).isEqualTo(bean2);
        System.out.println(bean1);
        System.out.println(bean2);
    }

    @Test
    @DisplayName("등록된 Bean 조희 실패")
    void findBeanX() {
        assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean(Member.class));
    }
}
