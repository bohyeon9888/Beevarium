package b203.varium.oauth2.service;

import b203.varium.broadcastStation.service.BroadcastStationService;
import b203.varium.jwt.JWTUtil;
import b203.varium.oauth2.dto.*;
import b203.varium.user.entity.UserEntity;
import b203.varium.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@Service
@RequiredArgsConstructor
public class OauthService {

    private final Environment env;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bcrypt;
    private final JWTUtil jwtUtil;
    private final BroadcastStationService stationService;

    public Map<String, Object> getAccessToken(String code, String provider) {
        System.out.println("================get access token==================");
        RestTemplate restTemplate = new RestTemplate();
        String clientId = env.getProperty("spring.security.oauth2.client.registration." + provider + ".client-id");
        System.out.println("client ID : " + clientId);
        String clientSecret = env.getProperty("spring.security.oauth2.client.registration." + provider + ".client-secret");
        String redirectUri = env.getProperty("spring.security.oauth2.client.registration." + provider + ".redirect-uri");
        String tokenUri = env.getProperty("spring.security.oauth2.client.provider." + provider + ".token-uri");

//        System.out.println("clientSecret : " + clientSecret);
//        System.out.println("redirectUri : " + redirectUri);
//        System.out.println("tokenUri : " + tokenUri);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("code", code);
        params.add("grant_type", "authorization_code");
        params.add("redirect_uri", redirectUri);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(tokenUri, request, Map.class);
        Map respBody = response.getBody();
        System.out.println(respBody);
        String accessToken = (String) respBody.get("access_token");

        return getUserResource(accessToken, provider);
    }

    public Map<String, Object> getUserResource(String accessToken, String provider) {
        System.out.println("================get user resource==================");
        String resourceUrl = env.getProperty("spring.security.oauth2.client.provider." + provider + ".user-info-uri");
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.setBearerAuth(accessToken);
        System.out.println(headers);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<Map> response = restTemplate.exchange(resourceUrl, HttpMethod.GET, entity, Map.class);

        Map respBody = response.getBody();
        System.out.println(respBody);

        OAuth2Response oAuth2Response = null;
        switch (provider) {
            case "google" -> oAuth2Response = new GoogleResponse(respBody);
            case "naver" -> oAuth2Response = new NaverResponse(respBody);
            case "kakao" -> {

                Map<String, Object> kakaoAccount = (Map<String, Object>) respBody.get("kakao_account");
                Map<String, Object> kakaoProfile = (Map<String, Object>) kakaoAccount.get("profile");
                oAuth2Response = new KakaoResponse(respBody, kakaoAccount, kakaoProfile);
            }
        }

        log.info(oAuth2Response.getName());
        return getUserInfo(oAuth2Response);
    }

    public Map<String, Object> getUserInfo(OAuth2Response oAuth2Response) {

        System.out.println("================get user info==================");
        String role = "ROLE_USER";

        saveUser(oAuth2Response);

        // JWT Token 생성
        String jwtToken = jwtUtil.createJwt(oAuth2Response.getName() + "#" + oAuth2Response.getProviderId().substring(0, 4), role, 18000000L);

        // Authentication 객체 생성
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                new CustomOAuth2User(oAuth2Response, role), null, Collections.singletonList(new SimpleGrantedAuthority(role))
        );

        // SecurityContext에 Authentication 객체 저장
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        Map<String, Object> resp = new HashMap<>();
        resp.put("status", "success");

        Map<String, String> data = new HashMap<>();
        data.put("profile_img_url", oAuth2Response.getProfileImg());
        data.put("token", "Bearer " + jwtToken); // 예시 토큰 값, 실제로는 생성된 토큰 사용

        resp.put("data", data);

        return resp;
    }

    public void saveUser(OAuth2Response oAuth2Response) {

        System.out.println("================save user info==================");
        String email = oAuth2Response.getEmail();
        String name = oAuth2Response.getName();
        String role = "ROLE_USER";
        String userCode = env.getProperty("user.provider." + oAuth2Response.getProvider() + ".code-name");

        UserEntity existUser = userRepository.findAllByEmailAndCodeName(email, userCode);
        Timestamp nowT = new Timestamp(System.currentTimeMillis());

        if (existUser != null) {

            existUser.setUsername(name + "#" + oAuth2Response.getProviderId().substring(0, 4));
            existUser.setProfileUrl(oAuth2Response.getProfileImg());
            int nowP = existUser.getPoint();
            existUser.setPoint(nowP + 50);
            existUser.setUpdatedDate(nowT);

            userRepository.save(existUser);
            log.debug("update UserInfo");

        } else {

            UserEntity userEntity = new UserEntity();
            userEntity.setUsername(name + "#" + oAuth2Response.getProviderId().substring(0, 4));
            userEntity.setUserId(oAuth2Response.getProviderId());
            userEntity.setPassword(bcrypt.encode(oAuth2Response.getProvider() + "bee" + oAuth2Response.getProviderId()));
            userEntity.setEmail(email);
            userEntity.setProfileUrl(oAuth2Response.getProfileImg());
            userEntity.setPoint(500);
            userEntity.setCodeName(userCode);
            userEntity.setRole(role);
            userEntity.setCreatedDate(nowT);
            userEntity.setUpdatedDate(nowT);
            userEntity.setStation(stationService.createBroadcastStation(userEntity));

            userRepository.save(userEntity);
            log.debug("save UserInfo");
        }
    }

}
