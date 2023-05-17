package transaction.account_transfer.repository.member.mybatis;

import org.apache.ibatis.annotations.Param;
import transaction.account_transfer.domain.member.Member;

import java.util.List;

public interface MemberMapper {
    void save(Member member);
    void update(@Param("id") String id, @Param("money") int money);
    void delete(String id);
    List<Member> findAll();
    Member findByLoginId(String loginId);
}
