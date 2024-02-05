package b203.varium.user.entity;


import b203.varium.Record;
import b203.varium.broadcastStation.entity.BroadcastStation;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
public class UserEntity extends Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_no")
    private int id; // user_no를 기본 키로 사용

    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_pw")
    private String password;

    @Column(name = "user_email")
    private String email;

    @Column(name = "user_nickname")
    private String username;

    @Column(name = "user_profile_url")
    private String profileUrl;

    @Column(name = "user_point")
    private Integer point;

    @Column(name = "user_role")
    private String role;

    @Column(name = "code_name")
    private String codeName;

    @OneToOne(mappedBy = "user")
    private BroadcastStation station;
}
