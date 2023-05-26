package hello.ex.service;

import hello.ex.domain.Member;
import hello.ex.exception.NoSuchIdPlzReTryException;
import hello.ex.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberRepository memberRepository;

    public Member login(String loginId, String password) throws NoSuchIdPlzReTryException {
        return memberRepository.findByLoginId(loginId);
    }
}
