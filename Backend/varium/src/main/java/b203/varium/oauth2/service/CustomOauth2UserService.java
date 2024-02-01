package b203.varium.oauth2.service;

import b203.varium.jwt.JWTUtil;
import b203.varium.oauth2.dto.*;
import b203.varium.user.entity.UserEntity;
import b203.varium.user.repository.UserRepository;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
public class CustomOauth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private final JWTUtil jwtUtil;
    private final BCryptPasswordEncoder bcrypt;
    private final Environment env;

    public CustomOauth2UserService(UserRepository userRepository, JWTUtil jwtUtil, BCryptPasswordEncoder bcrypt, Environment env) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.bcrypt = bcrypt;
        this.env = env;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String, Object> attribute = oAuth2User.getAttributes();
        System.out.println(attribute);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userCode = env.getProperty("user.provider." + registrationId + ".code-name");
        OAuth2Response oAuth2Response = null;

        if (registrationId.equals("naver")) {
            oAuth2Response = new NaverResponse(attribute);

        } else if (registrationId.equals("google")) {

            oAuth2Response = new GoogleResponse(attribute);

        } else if (registrationId.equals("kakao")) {
            Map<String, Object> kakaoAccount = (Map<String, Object>) attribute.get("kakao_account");
            oAuth2Response = new KakaoResponse(attribute, kakaoAccount, (Map<String, Object>) kakaoAccount.get("profile"));
        } else {

            return null;
        }
        String email = oAuth2Response.getEmail();
        UserEntity existData = userRepository.findByEmail(email);

        String role = "ROLE_USER";
        if (existData == null) {

            UserEntity userEntity = new UserEntity();
            userEntity.setUsername(oAuth2Response.getName());
            userEntity.setUserId(oAuth2Response.getProviderId());
            userEntity.setPassword(bcrypt.encode(oAuth2Response.getProvider() + "bee" + oAuth2Response.getProviderId()));
            userEntity.setEmail(oAuth2Response.getEmail());
            userEntity.setProfileUrl(oAuth2Response.getProfileImg());
            userEntity.setRole(role);
            userEntity.setCodeName(userCode);
            userEntity.setPoint(500);

            userRepository.save(userEntity);
        } else {

            existData.setUsername(oAuth2Response.getName());
            existData.setUserId(oAuth2Response.getProviderId());
            existData.setPassword(bcrypt.encode(oAuth2Response.getProvider() + "bee" + oAuth2Response.getProviderId()));
            existData.setEmail(email);
            existData.setProfileUrl(oAuth2Response.getProfileImg());
            int nowP = existData.getPoint();
            existData.setPoint(nowP + 50);

            userRepository.save(existData);
        }

        // Authentication 객체 생성
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                new CustomOAuth2User(oAuth2Response, role), null, Collections.singletonList(new SimpleGrantedAuthority(role))
        );

        // SecurityContext에 Authentication 객체 저장
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        return new CustomOAuth2User(oAuth2Response, role);
    }
    
}
