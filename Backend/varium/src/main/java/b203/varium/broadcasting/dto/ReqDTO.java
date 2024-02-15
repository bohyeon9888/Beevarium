package b203.varium.broadcasting.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class ReqDTO {
    @NotNull(message = "제목은 필수 입력값입니다.")
    private String broadcastingTitle;

    private List<String> tagList;
}