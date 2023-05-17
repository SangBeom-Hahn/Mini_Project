package transaction.account_transfer.repository.member;

import transaction.account_transfer.domain.member.Member;

import java.util.List;

public interface MemberRepository {
    Member save(Member member);

    List<Member> findAll();

    Member findByLoginId(String loginId);

    void update(String id, int money);

    void delete(String id);
}
