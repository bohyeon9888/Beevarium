package b203.varium.user.dto;

import b203.varium.follow.dto.FollowRespDTO;
import lombok.Data;

import java.util.List;

@Data
public class MyPageRespDTO {
    private String username;
    private String profileUrl;
    private int point;
    private List<FollowRespDTO> subscribeList;
}
