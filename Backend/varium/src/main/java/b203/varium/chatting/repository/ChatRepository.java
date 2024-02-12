package b203.varium.chatting.repository;

import b203.varium.chatting.entity.ChatEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatRepository extends MongoRepository<ChatEntity, String> {
}
