package b203.varium.user.service;

import b203.varium.user.dto.JoinDTO;
import b203.varium.user.entity.UserEntity;
import b203.varium.user.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bcryptEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bcryptEncoder) {
        this.userRepository = userRepository;
        this.bcryptEncoder = bcryptEncoder;
    }

    public void joinUser(JoinDTO joinDTO) {
        String username = joinDTO.getUsername();
        String userid = joinDTO.getUserId();
        String password = joinDTO.getPassword();
        String email = joinDTO.getEmail();

        Boolean existName = userRepository.existsByUsername(username);
        Boolean existEmail = userRepository.existsByEmail(email);

        if (existName || existEmail) {
            // 에러 처리
            log.error("existed Member");
            return;
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
    }
}
