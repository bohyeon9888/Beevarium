package b203.varium.chatting.dto;

import lombok.Data;

import java.util.List;

@Data
public class SaveReqDTO {
    private List<ChatDTO> chatting;
}
