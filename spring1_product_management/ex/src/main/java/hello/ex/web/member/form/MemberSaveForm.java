package hello.ex.web.member.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class MemberSaveForm {
    @NotBlank
    private String userName;
    @NotBlank
    @Size(min = 2, max = 16)
    private String loginId;
    @NotBlank
    @Size(min = 2, max = 16)
    private String password;
}
