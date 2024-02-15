package b203.varium.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsMvcConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        corsRegistry.addMapping("/**")
                .allowedMethods("GET", "PATCH", "PUT", "POST", "DELETE")
                .allowedHeaders("*")
                .allowedOriginPatterns("*"); // 모든 출처 허용을 위해 이렇게 변경
    }
}
