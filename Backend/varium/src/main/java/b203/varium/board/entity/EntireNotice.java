package b203.varium.board.entity;

import b203.varium.Record;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "entire_notice") // 여기서 테이블 이름을 정확하게 지정
public class EntireNotice extends Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entire_notice_no")
    private int id;

    @Column(name = "entire_notice_title")
    private String entireNoticeTitle;

    @Column(name = "entire_notice_content")
    private String entireNoticeContent;

}
