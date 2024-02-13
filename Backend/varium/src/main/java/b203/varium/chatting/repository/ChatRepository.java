package b203.varium.chatting.repository;

import b203.varium.chatting.entity.ChatEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChatRepository extends MongoRepository<ChatEntity, String> {

    List<ChatEntity> findAllByStreamerId(String streamerId);

    List<ChatEntity> findAllByStreamerIdAndUsername(String streamerId, String username);
}
