package b203.varium.chatting.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter @Setter
public class UserInfoDTO {
    private String username;
    private String email;
    private Timestamp createdDate;
    private List<ChatDTO> chatting;
}
