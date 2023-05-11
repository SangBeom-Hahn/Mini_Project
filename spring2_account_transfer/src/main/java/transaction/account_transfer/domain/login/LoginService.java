package transaction.account_transfer.domain.login;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import transaction.account_transfer.domain.member.Member;
import transaction.account_transfer.domain.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberRepository memberRepository;
    public Member login(String loginId, String password) {
        return memberRepository.findByLoginId(loginId);
    }
}
