package b203.varium.hashtag.entity;

import b203.varium.Record;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tag")
public class TagEntity extends Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_no")
    private int id;

    @Column(name = "tag_text")
    private String tagText;
    
//    @ManyToOne
//    @JoinColumn(name = "tag_no")
//    private HashTag hashTag;

}
