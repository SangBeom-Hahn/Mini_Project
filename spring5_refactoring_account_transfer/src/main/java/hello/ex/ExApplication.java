package hello.ex;

import hello.ex.config.TestDataInit;
import hello.ex.repository.member.MemberRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class ExApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExApplication.class, args);
	}

	@Bean
	@Profile("local")
	public TestDataInit testDataInit(MemberRepository memberRepository) {
		return new TestDataInit(memberRepository);
	}
}
