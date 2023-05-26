package hello.ex.repository.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MemberSaveDTO {
    @NotBlank
    private String userName;
    @NotBlank
    @Size(min = 2, max = 16)
    private String loginId;
    @NotBlank
    @Size(min = 2, max = 16)
    private String password;
}
