package transaction.account_transfer.repository.member.mybatis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import transaction.account_transfer.domain.member.Member;
import transaction.account_transfer.repository.member.MemberRepository;

import java.util.List;

@Slf4j
//@Repository
@RequiredArgsConstructor
public class MyBatisMemberRepository implements MemberRepository {
    private final MemberMapper memberMapper;

    @Override
    public Member save(Member member) {
        memberMapper.save(member);
        return member;
    }

    @Override
    public List<Member> findAll() {
        return memberMapper.findAll();
    }

    @Override
    public Member findByLoginId(String loginId) {
        return memberMapper.findByLoginId(loginId);
    }

    @Override
    public void update(String id, int money) {
        memberMapper.update(id, money);
    }

    @Override
    public void delete(String id) {

    }
}
