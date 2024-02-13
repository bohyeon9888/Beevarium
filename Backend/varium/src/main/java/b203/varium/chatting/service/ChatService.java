package b203.varium.chatting.service;

import b203.varium.broadcastStation.repository.BroadcastStationRepository;
import b203.varium.chatting.dto.ChatDTO;
import b203.varium.chatting.dto.UserInfoDTO;
import b203.varium.chatting.entity.ChatEntity;
import b203.varium.chatting.repository.ChatRepository;
import b203.varium.follow.entity.FollowRelation;
import b203.varium.follow.respository.FollowRelationRepository;
import b203.varium.user.entity.UserEntity;
import b203.varium.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final MongoTemplate mongoTemplate;
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;
    private final BroadcastStationRepository broadcastStationRepository;
    private final FollowRelationRepository followRepository;

    public Map<String, Object> saveChatting(String streamerId, List<ChatDTO> chatting) {
        Map<String, Object> resp = new HashMap<>();
        Map<String, String > msg = new HashMap<>();

        List<ChatEntity> chatRecord = new ArrayList<>();

        for (ChatDTO chat : chatting) {
            ChatEntity data = new ChatEntity();
            data.setStreamerId(streamerId);
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

    public Map<String, Object> viewAllChatting(String streamerName) {
        UserEntity user = userRepository.findByUsername(streamerName);

        List<ChatEntity> chattingList = chatRepository.findAllByStreamerId(user.getUserId());
        List<ChatDTO> result = new ArrayList<>();

        for (ChatEntity chat : chattingList) {
            ChatDTO data = new ChatDTO();
            data.setMessage(chat.getMessage());
            data.setName(chat.getUsername());
            data.setTime(chat.getTime());

            result.add(data);
        }

        Map<String, Object> resp = new HashMap<>();
        Map<String, Object> msg = new HashMap<>();

        resp.put("status", "success");
        msg.put("chattingList", result);
        resp.put("data", msg);

        return resp;
    }

    public Map<String, Object> viewUserChatting(String streamerName, String username) {
        Map<String, Object> resp = new HashMap<>();
        Map<String, Object> msg = new HashMap<>();

        UserEntity user = userRepository.findByUsername(username);
        if (user == null) {
            resp.put("status", "fail");
            msg.put("msg", "there is no user");
            resp.put("data", msg);
            return resp;
        }
        UserEntity streamer = userRepository.findByUsername(streamerName);
        int stationId = broadcastStationRepository.findByUser_Username(streamerName).getId();

        List<ChatEntity> chattingList = chatRepository.findAllByStreamerIdAndUsername(streamer.getUserId(), username);
        List<ChatDTO> result = new ArrayList<>();

        if (!chattingList.isEmpty()) {
            for (ChatEntity chat : chattingList) {
                ChatDTO data = new ChatDTO();
                data.setMessage(chat.getMessage());
                data.setName(chat.getUsername());
                data.setTime(chat.getTime());

                result.add(data);
            }
        }

        UserInfoDTO userinfo = new UserInfoDTO();
        FollowRelation follow = followRepository.findByBroadcastStation_IdAndFollower_Username(stationId, username);
        if (follow == null) {
            userinfo.setCreatedDate(null);
        } else {
            userinfo.setCreatedDate(follow.getCreatedDate());
        }
        userinfo.setUsername(username);
        userinfo.setEmail(user.getEmail());
        userinfo.setChatting(result);

        resp.put("status", "success");
        msg.put("userInfo", userinfo);
        resp.put("data", msg);

        return resp;
    }
}
