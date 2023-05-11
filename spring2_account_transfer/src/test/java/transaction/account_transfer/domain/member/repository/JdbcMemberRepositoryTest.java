package transaction.account_transfer.domain.member.repository;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import transaction.account_transfer.domain.member.Member;

import javax.sql.DataSource;
@Slf4j
@SpringBootTest
class JdbcMemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

//    @TestConfiguration
//    static class TestConfig{
//        private final DataSource dataSource;
//
//        public TestConfig(DataSource dataSource) {
//            this.dataSource = dataSource;
//        }
//
//        @Bean
//        MemberRepository memberRepository() {
//            return new JdbcMemberRepository(dataSource);
//        }
//    }

    @AfterEach
    void afterEach() {
        memberRepository.delete("apple");
    }
    @Test
    void save() {
        Member member = new Member("apple", 10000);
        log.info("{}", member);
        memberRepository.save(member);

        Member findMember = memberRepository.findByLoginId(member.getLoginId());
        log.info("{}", findMember);
        Assertions.assertThat(findMember).isEqualTo(member);
    }
}