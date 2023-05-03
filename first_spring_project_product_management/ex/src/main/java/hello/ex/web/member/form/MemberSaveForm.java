package hello.ex.web.member.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class MemberSaveForm {
    @NotBlank
    private String userName;
    @NotBlank
    private String loginId;
    @NotBlank
    private String password;
}
