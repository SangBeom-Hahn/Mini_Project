package transaction.account_transfer.domain.member;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.NumberFormat;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Member {
    private String loginId;
    private String password;
    private String username;
    @NumberFormat(pattern = "###,###")
    private int money;

    public Member(String loginId, int money) {
        this.loginId = loginId;
        this.money = money;
    }

    public Member(String loginId, String password, String username, int money) {
        this.loginId = loginId;
        this.password = password;
        this.username = username;
        this.money = money;
    }

    public Member() {
    }
}
