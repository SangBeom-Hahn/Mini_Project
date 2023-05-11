package transaction.account_transfer.domain.member.repository;

import transaction.account_transfer.domain.member.Member;

import java.util.List;

public interface MemberRepository {
    Member save(Member member);

    Member findById(String id);

    List<Member> findAll();

    Member findByLoginId(String loginId);

    void update(String id, int money);

    void delete(String id);
}
