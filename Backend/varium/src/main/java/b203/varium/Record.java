package b203.varium;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;


//슈퍼클래스
@MappedSuperclass
@Getter
@Setter
public abstract class Record {

    @Column(name = "created_date")
    private Timestamp createdDate;

    @Column(name = "updated_date")
    private Timestamp updatedDate;

    // 기타 필드 및 메소드...


//@Entity
//@Getter
//@Setter
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "DTYPE")
//@MappedSuperclass
//public class Record {
//    @Id
//    private String createdDate;
//    private String updatedDate;


//    private String deletedDate;
//
//    @Enumerated(EnumType.STRING)
//    private State state;    //ACTIVATED(활성화), INACTIVATED(비활성화), HOLD(보류)

    // 생성자, 게터, 세터 등 필요한 코드 추가
}
