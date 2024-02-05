package b203.varium.hashtag.entity;

import b203.varium.Record;
import b203.varium.broadcasting.entity.Broadcasting;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "hashtag")
public class HashTag extends Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hashtag_no")
    private int id;

    @ManyToOne
    @JoinColumn(name = "broadcasting_no")
    private Broadcasting broadcasting;

    @ManyToOne
    @JoinColumn(name = "tag_no")
    private TagEntity tag;

}
