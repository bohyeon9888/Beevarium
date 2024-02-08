package b203.varium.video.entity;

import b203.varium.Record;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "thumbnail_file")
public class ThumbnailFile extends Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_no")
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "video_no", referencedColumnName = "video_no")
    private Video video;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "original_name")
    private String originName;

    @Column(name = "save_name")
    private String savedName;
}
