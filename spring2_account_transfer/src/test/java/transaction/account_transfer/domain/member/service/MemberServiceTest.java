package transaction.account_transfer.domain.member.service;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import transaction.account_transfer.domain.member.Member;
import transaction.account_transfer.domain.member.repository.MemberRepository;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@SpringBootTest
class MemberServiceTest {
    public static final String MEMBER_A = "memberA";
    public static final String MEMBER_B = "memberB";

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @AfterEach
    void afterEach() {
        memberRepository.delete(MEMBER_A);
        memberRepository.delete(MEMBER_B);
    }

    /**
     * 계좌 이체 성공 케이스
     */
    @Test
    void accountTransfer() {
        // given
        Member memberA = new Member(MEMBER_A, 10000);
        Member memberB = new Member(MEMBER_B, 10000);
        memberRepository.save(memberA);
        memberRepository.save(memberB);
        log.info("[{}], [{}]", memberA, memberB);
        
        // when
        memberService.accountTransfer(memberA.getLoginId(), memberB.getLoginId(), 2000);
        log.info("[{}], [{}]", memberA, memberB);

        // then
        Member findMemberA = memberRepository.findByLoginId(memberA.getLoginId());
        Member findMemberB = memberRepository.findByLoginId(memberB.getLoginId());
        assertThat(findMemberA.getMoney()).isEqualTo(8000);
        assertThat(findMemberB.getMoney()).isEqualTo(12000);
    }

    /**
     * 계좌 이체 실패 케이스
     * 찾을 수 없는 사용자 레포에서 NoSUchElementEx 발생
     * 서비스는 런타임으로 흘림
     */
    @Test
    void accountTransferEx() {
        // given
        Member memberA = new Member(MEMBER_A, 10000);
        Member memberB = new Member(MEMBER_B, 10000);
        memberRepository.save(memberA);
        memberRepository.save(memberB);

        // when
        assertThatThrownBy(() -> memberService.accountTransfer(memberA.getLoginId(), "banana", 2000))
                .isInstanceOf(NoSuchElementException.class);

        // then
        Member findMemberA = memberRepository.findByLoginId(memberA.getLoginId());
        Member findMemberB = memberRepository.findByLoginId(memberB.getLoginId());
        assertThat(findMemberA.getMoney()).isEqualTo(10000);
        assertThat(findMemberB.getMoney()).isEqualTo(10000);
    }

    /**
     * 이미 존재해서 서비스에서 잡는 경우
     */
    @Test
    void join() {
        Member memberA = new Member(MEMBER_A, 10000);
        Member memberB = new Member(MEMBER_A, 10000);
        Member reMemberA = memberService.join(memberA);
        log.info("A = {}", reMemberA);
        Member reMemberB = memberService.join(memberB);
        log.info("B = {}", reMemberB);
    }

    /**
     * 이미 존재해서 서비스에서 던지는 경우
     */
    @Test
    void joinThrow2() {
        Member memberA = new Member(MEMBER_A, 10000);
        Member memberB = new Member(MEMBER_A, 10000);
        memberService.joinThrow(memberA);

        assertThatThrownBy(() -> memberService.joinThrow(memberB))
                .isInstanceOf(IllegalStateException.class);
    }
}