package b203.varium.user.controller;

import b203.varium.user.dto.JoinDTO;
import b203.varium.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

        return "user Controller";
    }

    @PostMapping("/join")
    public String joinUser(JoinDTO joinDTO) {

        System.out.println(joinDTO.getUsername());
        userService.joinUser(joinDTO);

        return "ok";
    }

}
