package hello.ex.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDTO {
    @NotBlank
    private String loginId;
    @NotBlank
    private String password;
}
