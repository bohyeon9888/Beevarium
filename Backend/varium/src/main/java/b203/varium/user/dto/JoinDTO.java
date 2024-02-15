package b203.varium.user.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinDTO {
    @NotNull(message = "사용자 이름은 필수 입력값입니다.")
    private String username;

    @NotNull(message = "사용자 아이디는 필수 입력값입니다.")
    private String userId;

    @NotNull(message = "비밀번호는 필수 입력값입니다.")
    private String password;

    @NotNull(message = "이메일은 필수 입력값입니다.")
    private String email;
}
