package yeo.nuel.lix.user;

public interface KakaoUserPort {

    UserPortResponse findUserFromKakao(String accessToken);
}
