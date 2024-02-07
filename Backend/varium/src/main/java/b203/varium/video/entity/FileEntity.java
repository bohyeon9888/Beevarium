package b203.varium.video.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "file_info")
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_no")
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "video_no", referencedColumnName = "video_no")
    private Video video;

    private String filePath;

    private String originName;

    private String savedName;
}
