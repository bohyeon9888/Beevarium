package b203.varium.board.entity;

import b203.varium.Record;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Entity
@Getter
@Setter
public class EntireNotice extends Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entire_notice_no")
    private Integer entireNoticeNo;

    private String entireNoticeTitle;

    private String entireNoticeContent;

}
