package transaction.account_transfer.web.member.controller.form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccountForm {
    @NotEmpty
    private String fromId;

    @NotEmpty
    private String toId;

    @NotNull
    private int money;
}
