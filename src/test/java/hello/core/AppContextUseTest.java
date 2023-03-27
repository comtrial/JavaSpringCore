package hello.core;

import hello.core.member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootTest
class AppContextUseTest {

	AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
//	@Test
//	void contextLoads() {
//		MemberService memberService = ac.getBean(MemberService.class);
//
//		memberService.findMember();
//	}

}
