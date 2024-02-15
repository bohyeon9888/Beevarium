package b203.varium.chatting.entity;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Document(collection = "chatting")
public class ChatEntity {

    @Field(name = "streamerId")
    private String streamerId;

    @Field(name = "name")
    private String username;

    @Field(name = "message")
    private String message;

    @Field(name = "time")
    private String time;
}
