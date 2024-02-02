package b203.varium;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;


//슈퍼클래스
@MappedSuperclass
public abstract class Record {

    @Id
    private String createdDate;
    private String updatedDate;


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
