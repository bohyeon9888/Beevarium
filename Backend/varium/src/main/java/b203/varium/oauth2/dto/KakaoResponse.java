package b203.varium.oauth2.dto;

import java.util.Map;

public class KakaoResponse implements OAuth2Response {

    private final Map<String, Object> attribute;
    private final Map<String, Object> kakaoAccount;
    private final Map<String, Object> kakaoProfile;

    public KakaoResponse(Map<String, Object> attribute, Map<String, Object> kakaoAccount, Map<String, Object> kakaoProfile) {
        this.attribute = attribute;
        this.kakaoAccount = kakaoAccount;
        this.kakaoProfile = kakaoProfile;
    }

    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getProviderId() {
        return attribute.get("id").toString();
    }

    @Override
    public String getEmail() {
        return kakaoAccount.get("email").toString();
    }

    @Override
    public String getName() {
        return kakaoProfile.get("nickname").toString();
    }

    @Override
    public String getProfileImg() {
        return kakaoProfile.get("profile_image_url").toString();
    }
}
