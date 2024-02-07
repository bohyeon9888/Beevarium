package b203.varium.config;

import b203.varium.jwt.CustomAuthenticationSuccessHandler;
import b203.varium.jwt.JWTFilter;
import b203.varium.jwt.JWTUtil;
import b203.varium.jwt.LoginFilter;
import b203.varium.oauth2.service.CustomOauth2UserService;
import b203.varium.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final UserRepository userRepository;
    private final CustomOauth2UserService customOAuth2UserService;
    private final JWTUtil jwtUtil;
    private final CustomAuthenticationSuccessHandler successHandler;
    private final AuthenticationConfiguration authenticationConfiguration;

    @Value("#{'${spring.security.permit-path}'.split(',')}")
    private String[] paths;

    public SecurityConfig(UserRepository userRepository, CustomOauth2UserService customOAuth2UserService, JWTUtil jwtUtil, CustomAuthenticationSuccessHandler successHandler, AuthenticationConfiguration authenticationConfiguration) {
        this.userRepository = userRepository;
        this.customOAuth2UserService = customOAuth2UserService;
        this.jwtUtil = jwtUtil;
        this.successHandler = successHandler;
        this.authenticationConfiguration = authenticationConfiguration;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(List.of("*")); // 모든 출처 허용
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowCredentials(true); // 주의: 실제 배포에서는 보안상의 이유로 이 설정을 신중하게 사용하세요.
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setMaxAge(3600L);
        configuration.setExposedHeaders(List.of("Authorization"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf.disable());
        http.cors(corsCustomizer -> corsCustomizer.configurationSource(corsConfigurationSource())); // CORS 설정 적용

        http.formLogin((login) -> login.disable());

        http.httpBasic((basic) -> basic.disable());

        http.oauth2Login(Customizer.withDefaults());

        http.oauth2Login((oauth2) -> oauth2
                .userInfoEndpoint((userInfoEndpointConfig) -> userInfoEndpointConfig.userService(customOAuth2UserService))
                .successHandler(successHandler));

        http.authorizeHttpRequests((auth) -> auth
                .requestMatchers(paths).permitAll()
                .anyRequest().authenticated());

        // JWT 주입
        http.addFilterBefore(new JWTFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);

        http.addFilterAt(new LoginFilter(userRepository, authenticationManager(authenticationConfiguration), jwtUtil), UsernamePasswordAuthenticationFilter.class);

        http.sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}