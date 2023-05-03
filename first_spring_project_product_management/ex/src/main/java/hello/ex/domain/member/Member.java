package hello.ex.domain.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member {
    private Long id;

    public Member(String userName, String loginId, String password) {
        this.userName = userName;
        this.loginId = loginId;
        this.password = password;
    }

    private String userName;
    private String loginId;
    private String password;
}
