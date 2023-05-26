package hello.ex.repository;

import hello.ex.domain.Member;
import hello.ex.exception.NoSuchIdPlzReTryException;
import hello.ex.repository.member.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
@Transactional
class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    void insert() throws NoSuchIdPlzReTryException {
        Member member = new Member("a", "b", "c", 10000);
        memberRepository.save(member);

        Member findMember = memberRepository.findByLoginId(member.getLoginId());

        assertThat(findMember).isEqualTo(member);
        assertThat(memberRepository.findAll().size()).isEqualTo(3);
    }
}