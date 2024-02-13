package b203.varium.chatting.controller;

import b203.varium.chatting.dto.ChatDTO;
import b203.varium.chatting.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.apache.http.protocol.HTTP;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Service
@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;

    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> listAllChatting() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String streamerName = auth.getName();

        return ResponseEntity.ok(chatService.viewAllChatting(streamerName));
    }

    @GetMapping("/show/{username}")
    public ResponseEntity<Map<String, Object>> listUserChatting(@PathVariable String username) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String streamerName = auth.getName();

        Map<String, Object> resp = chatService.viewUserChatting(streamerName, username);
        if (resp.get("status").equals("success")) {
            return ResponseEntity.ok(resp);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
        }
    }

}
