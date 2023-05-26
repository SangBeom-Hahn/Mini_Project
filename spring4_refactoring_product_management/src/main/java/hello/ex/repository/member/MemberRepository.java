package hello.ex.repository.member;

import hello.ex.domain.member.Member;
import hello.ex.repository.member.dto.MemberSaveDTO;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemberRepository {
    private static final Map<Long, Member> store = new HashMap<>();
    private static Long sequence = 0L;

    public Member save(MemberSaveDTO memberSaveDTO) {
        Member member = new Member(memberSaveDTO.getUserName(), memberSaveDTO.getLoginId(), memberSaveDTO.getPassword());
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public Optional<Member> findByLoginId(String loginId) {
        return findAll().stream()
                .filter(member -> member.getLoginId().equals(loginId))
                .findAny();
    }
}
