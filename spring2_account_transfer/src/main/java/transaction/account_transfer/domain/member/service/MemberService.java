package transaction.account_transfer.domain.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import transaction.account_transfer.domain.member.Member;
import transaction.account_transfer.domain.member.repository.MemberRepository;

import java.util.Random;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    /**
     * 계좌 이체 중 없는 id인 경우 예외처리 해야 함
     * 없다고 레포에서 예외가 터지지 않으니 서비스에서 예외를 터뜨리고
     * 해결은 못하니 던진다.
     * @param fromId
     * @param toId
     * @param money
     */
    public void accountTransfer(String fromId, String toId, int money) {
        bizlogic(fromId, toId, money);
    }

    /**
     * 회원가입 중 이미 존재하는 회원
     * @param member
     * @return
     */
    // 버전 1 비즈니스 로직상 사용자에서 예외를 잡아보자는 경우, 이때도 그냥 예외로 던질 수 있다.
    public Member join(Member member) {
        try {
            memberRepository.save(member);
            return member;
        } catch (DuplicateKeyException e) {
            String retryId = generateNewId(member.getLoginId());
            member.setLoginId(retryId);
            memberRepository.save(member);
            return member;
        }
    }


    // 버전 2 그냥 예외로 던져버리는 경우
    public Member joinThrow(Member member) {
        Member findMember = memberRepository.findByLoginId(member.getLoginId());

        // 이미 존재하지 않으면 null이 들어오나 단위 테스트로 확인해야 함
        if (findMember != null) {
            throw new IllegalStateException("이미 존재하는 회원임");
        }

        memberRepository.save(member);
        return member;
    }

    private void bizlogic(String fromId, String toId, int money) {
        Member fromMember = memberRepository.findByLoginId(fromId);
        Member toMember = memberRepository.findByLoginId(toId);
        /**
         * 레포를 사용하지만 서비스에서 로직상 발생한 예외
         * -> 인 줄 알았는데 레포에서 예외가 발생하더라 서비스는 트랜잭션으로 자연스럽게 던진다.
         */

        /**
         * 이게 필요가 없는 것이었다.
         * if (fromMember == null && toMember == null) {
         *     throw new NoSuchElementException("member not found id = " + id);
         * }
         */

        log.info("계좌 이체 후 적용 금액 = {}", fromMember.getMoney() - money);
        memberRepository.update(fromId, fromMember.getMoney() - money);
        memberRepository.update(toId, toMember.getMoney() + money);
    }

    private String generateNewId(String loginId) {
        return loginId + new Random().nextInt(10);
    }
}
