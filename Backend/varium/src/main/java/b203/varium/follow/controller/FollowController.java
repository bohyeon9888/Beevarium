package b203.varium.follow.controller;

import b203.varium.follow.dto.FollowDTO;
import b203.varium.follow.service.FollowRelationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@RestController
@RequestMapping("/follow")
public class FollowController {

    private final FollowRelationService followRelationService;

    public FollowController(FollowRelationService followRelationService) {
        this.followRelationService = followRelationService;
    }

    // 방송국이 보는 자기 팔로워들
    @GetMapping("/view/{stationId}")
    public void viewFollower(@PathVariable int stationId) {

    }

    // 유저가 보는 자신의 구독목록
    @GetMapping("/view")
    public void viewFollowing() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        log.info(auth.getName());

        
    }

    @GetMapping("/regist/{stationId}")
    public ResponseEntity<Map<String, String>> registFollow(@PathVariable int stationId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        log.info(auth.getName());

        FollowDTO followDTO = new FollowDTO();
        followDTO.setStationId(stationId);
        followDTO.setUsername(auth.getName());

        return ResponseEntity.ok(followRelationService.saveFollowRelation(followDTO));
    }

    @DeleteMapping("/deleteB/{stationId}")
    public void deleteFollow(@PathVariable int stationId) {

    }

    @DeleteMapping("/deleteF/{stationId}")
    public void deleteFollower(@PathVariable int stationId, @RequestParam int userNo) {

    }
}
