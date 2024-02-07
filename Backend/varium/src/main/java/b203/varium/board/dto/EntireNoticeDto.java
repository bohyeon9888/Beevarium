package b203.varium.board.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EntireNoticeDto {

    private Integer entireNoticeNo;
    private String entireNoticeTitle;
    private String entireNoticeContent;
    private Timestamp createdDate;
    private Timestamp updatedDate;
}
