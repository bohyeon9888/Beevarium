package b203.varium.user.controller;

import b203.varium.user.dto.JoinDTO;
import b203.varium.user.dto.NameReqDTO;
import b203.varium.user.dto.PwReqDTO;
import b203.varium.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 마이페이지 조회
    @GetMapping("/mypage")
    public ResponseEntity<Map<String, Object>> viewMyPage() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        // 구독 목록, 프로필 사진, 닉네임, 포인트

        Map<String, Object> result = new HashMap<>();
        if (userService.viewUserDetail(username) != null) {
            result.put("status", "success");
            result.put("data", userService.viewUserDetail(username));
        } else {
            result.put("status", "fail");
            Map<String, String> msg = new HashMap<>();
            msg.put("msg", "유저 정보가 없습니다.");
            result.put("data", msg);
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping("/signup")
    public ResponseEntity<Map<String, String>> joinUser(@RequestBody JoinDTO joinDTO) {

        System.out.println(joinDTO.getUsername());
        Map<String, String> result = userService.joinUser(joinDTO);

        return ResponseEntity.ok(result);
    }

    // 프로필사진 변경 api


    // 닉네임 중복확인 api
    @GetMapping("/check/{newName}")
    public ResponseEntity<Map<String, String>> checkUsername(@PathVariable String newName) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        return ResponseEntity.ok(userService.existUsername(username, newName));
    }

    // 닉네임, 비밀번호 수정 api
    @PostMapping("/update/nickname")
    public ResponseEntity<Map<String, Object>> updateUsername(@RequestBody NameReqDTO nameReqDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return ResponseEntity.ok(userService.updateName(auth.getName(), nameReqDTO.getNickname()));
    }

    @PostMapping("/update/pw")
    public ResponseEntity<Map<String, Object>> updatePassword(@RequestBody PwReqDTO pwReqDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return ResponseEntity.ok(userService.updatePassword(auth.getName(), pwReqDTO.getPassword()));
    }

}
