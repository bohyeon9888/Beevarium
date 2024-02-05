package b203.varium.video.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class ReplayVideo extends Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer replayVideoNo;

    private String replayVideoTextUrl;

    // Constructors, getters and setters
}
