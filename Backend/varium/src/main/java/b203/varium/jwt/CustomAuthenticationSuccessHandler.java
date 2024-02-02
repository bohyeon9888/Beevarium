package b203.varium.jwt;

import b203.varium.user.entity.UserEntity;
import b203.varium.user.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final JWTUtil jwtUtil;
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public CustomAuthenticationSuccessHandler(JWTUtil jwtUtil, UserRepository userRepository) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();

        String role = auth.getAuthority();
        System.out.println(authentication.getName());
        UserEntity userData = userRepository.findByUsername(authentication.getName());

        String token = jwtUtil.createJwt(authentication.getName(), role, 60 * 60 * 60 * 60L);
        System.out.println(token);

        Map<String, Object> resp = new HashMap<>();
        resp.put("status", "success");
        Map<String, String> data = new HashMap<>();
        data.put("token", "Bearer " + token); // 예시 토큰 값, 실제로는 생성된 토큰 사용
        data.put("profile_img_url", userData.getProfileUrl());

        resp.put("data", data);

        response.setContentType("application/json;charset=UTF-8");
        response.addHeader("Authorization", "Bearer " + token);

        response.getWriter().write(objectMapper.writeValueAsString(resp));

    }
}
