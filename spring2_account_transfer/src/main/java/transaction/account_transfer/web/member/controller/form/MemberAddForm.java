package transaction.account_transfer.web.member.controller.form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;
@Data
public class MemberAddForm {
    @NotEmpty
    @Size(max = 16)
    private String loginId;
    @NotEmpty
    @Size(max = 16)
    private String password;
    @NotEmpty
    private String username;
    @NumberFormat(pattern = "###,###")
    @NotNull
    private int money;
}
