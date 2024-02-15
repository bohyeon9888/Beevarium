package b203.varium.hashtag.entity;

import b203.varium.Record;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "tag")
public class TagEntity extends Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_no")
    private int id;

    @Column(name = "tag_text")
    private String tagText;

    @OneToMany(mappedBy = "tag", cascade = CascadeType.REMOVE)
    private List<HashTag> hashTagList;


}
