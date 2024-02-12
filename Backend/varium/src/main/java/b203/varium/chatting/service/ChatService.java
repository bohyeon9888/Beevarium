package b203.varium.chatting.service;

import b203.varium.chatting.dto.ChatDTO;
import b203.varium.chatting.entity.ChatEntity;
import b203.varium.chatting.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final MongoTemplate mongoTemplate;
    private final ChatRepository chatRepository;

    public Map<String, Object> saveChatting(String streamerId, List<ChatDTO> chatting) {
        Map<String, Object> resp = new HashMap<>();
        Map<String, String > msg = new HashMap<>();

        List<ChatEntity> chatRecord = new ArrayList<>();

        for (ChatDTO chat : chatting) {
            ChatEntity data = new ChatEntity();
            data.setId(streamerId);
            data.setUsername(chat.getName());
            data.setMessage(chat.getMessage());
            data.setTime(chat.getTime());

            chatRecord.add(data);
        }

        if (!chatRecord.isEmpty()) {
            mongoTemplate.insertAll(chatRecord);
            msg.put("msg", "success to save chatting");
            resp.put("data", msg);
            resp.put("status", "success");

            return resp;
        }

        msg.put("msg", "there is no chatting!");
        resp.put("data", msg);
        resp.put("status", "fail");

        return resp;
    }


}
