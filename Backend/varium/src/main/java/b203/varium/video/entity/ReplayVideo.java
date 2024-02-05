package b203.varium.video.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class ReplayVideo extends Video {


    // ClipVideo specific attributes (if any)
    // `@Id` 어노테이션 제거
    // (상속받는 경우 기본 키(@Id)는 상위 클래스에서만 정의해야 함)
    // `replayVideoNo` 필드 제거, `videoNo`를 사용

    private String replayVideoTextUrl;

    // Constructors, getters and setters
}
