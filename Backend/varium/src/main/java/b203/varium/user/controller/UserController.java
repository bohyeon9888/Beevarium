package b203.varium.user.controller;

import b203.varium.user.dto.CustomUserDetails;
import b203.varium.user.dto.JoinDTO;
import b203.varium.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@Controller
@ResponseBody
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/my")
    public String userP() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUserDetails = (CustomUserDetails) auth.getPrincipal();

        log.info(customUserDetails.getUsername());
        return "user controller";
    }

    @PostMapping("/join")
    public ResponseEntity<Map<String, String>> joinUser(JoinDTO joinDTO) {

        System.out.println(joinDTO.getUsername());
        Map<String, String> result = userService.joinUser(joinDTO);

        return ResponseEntity.ok(result);
    }

}
