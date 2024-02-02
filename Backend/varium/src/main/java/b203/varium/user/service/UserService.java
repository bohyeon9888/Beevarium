package b203.varium.user.service;

import b203.varium.user.dto.JoinDTO;
import b203.varium.user.entity.UserEntity;
import b203.varium.user.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bcryptEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bcryptEncoder) {
        this.userRepository = userRepository;
        this.bcryptEncoder = bcryptEncoder;
    }

    public Map<String, String> joinUser(JoinDTO joinDTO) {

        Map<String, String> resp = new HashMap<>();

        String username = joinDTO.getUsername();
        String userid = joinDTO.getUserId();
        String password = joinDTO.getPassword();
        String email = joinDTO.getEmail();

        if (userRepository.existsByUsername(username)) {
            resp.put("status", "fail");
            resp.put("msg", "이미 존재하는 사용자 이름입니다.");
            return resp;
        } else if (userRepository.existsByEmail(email)) {
            resp.put("status", "fail");
            resp.put("msg", "이미 존재하는 사용자 이메일입니다.");
            return resp;
        } else if (userRepository.existsByUserId(userid)) {
            resp.put("status", "fail");
            resp.put("msg", "이미 존재하는 사용자 아이디입니다.");
            return resp;
        }

        UserEntity data = new UserEntity();
        data.setUserId(userid);
        data.setUsername(username);
        data.setPassword(bcryptEncoder.encode(password));
        data.setEmail(email);
        data.setProfileUrl("none");
        data.setPoint(500);
        data.setRole("ROLE_USER");
        data.setCodeName("U1");

        userRepository.save(data);
        log.debug("join success!");

        resp.put("status", "success");
        resp.put("msg", "Join Success");

        return resp;
    }
}
