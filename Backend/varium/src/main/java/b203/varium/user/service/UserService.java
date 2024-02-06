package b203.varium.user.service;

import b203.varium.broadcastStation.service.BroadcastStationService;
import b203.varium.follow.dto.FollowRespDTO;
import b203.varium.follow.service.FollowRelationService;
import b203.varium.user.dto.JoinDTO;
import b203.varium.user.dto.MyPageRespDTO;
import b203.varium.user.entity.UserEntity;
import b203.varium.user.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bcryptEncoder;
    private final BroadcastStationService stationService;
    private final FollowRelationService followService;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bcryptEncoder, BroadcastStationService stationService, FollowRelationService followService) {
        this.userRepository = userRepository;
        this.stationService = stationService;
        this.bcryptEncoder = bcryptEncoder;
        this.followService = followService;
    }

    @Transactional
    public Map<String, String> joinUser(JoinDTO joinDTO) {

        Map<String, String> resp = new HashMap<>();

        String username = joinDTO.getUsername();
        String userid = joinDTO.getUserId();
        String password = joinDTO.getPassword();
        String email = joinDTO.getEmail();
        Timestamp nowT = new Timestamp(System.currentTimeMillis());

        Map<String, String> duplicationCheckResult = checkForDuplicates(username, email, userid);

        // 중복이 있는 경우, 검사 결과 반환
        if (!duplicationCheckResult.isEmpty()) {
            return duplicationCheckResult;
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
        data.setCreatedDate(nowT);
        data.setUpdatedDate(nowT);
        data.setStation(stationService.createBroadcastStation(data));

        userRepository.save(data);
        log.debug("join success!");


        resp.put("status", "success");
        resp.put("msg", "Join Success");

        return resp;
    }

    public Map<String, String> checkForDuplicates(String username, String email, String userid) {
        Map<String, String> resp = new HashMap<>();

        if (userRepository.existsByUsername(username)) {
            resp.put("status", "fail");
            resp.put("msg", "이미 존재하는 사용자 이름입니다.");
        } else if (userRepository.existsByEmail(email)) {
            resp.put("status", "fail");
            resp.put("msg", "이미 존재하는 사용자 이메일입니다.");
        } else if (userRepository.existsByUserId(userid)) {
            resp.put("status", "fail");
            resp.put("msg", "이미 존재하는 사용자 아이디입니다.");
        }

        return resp;
    }

    public MyPageRespDTO viewUserDetail(String username) {

        UserEntity user = userRepository.findByUsername(username);
        List<FollowRespDTO> followList = followService.getFollowList(username);

        MyPageRespDTO respDTO = new MyPageRespDTO();
        respDTO.setUsername(username);
        respDTO.setProfileUrl(user.getProfileUrl());
        respDTO.setPoint(user.getPoint());
        respDTO.setSubscribeList(followList);

        return respDTO;
    }

    public Map<String, String> existUsername(String nowName, String newName) {
        Map<String, String> result = new HashMap<>();

        if (nowName.equals(newName)) {
            result.put("status", "fail");
            result.put("msg", "같은 이름입니다.");
            return result;
        }

        if (userRepository.existsByUsername(newName)) {
            result.put("status", "fail");
            result.put("msg", "이미 존재하는 이름입니다.");
        } else {
            result.put("status", "success");
            result.put("msg", "변경 가능한 이름입니다.");
        }

        return result;
    }
}
