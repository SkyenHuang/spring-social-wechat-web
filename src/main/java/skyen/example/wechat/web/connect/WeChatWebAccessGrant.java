package skyen.example.wechat.web.connect;

import org.springframework.social.oauth2.AccessGrant;

public class WeChatWebAccessGrant extends AccessGrant {

    private String openId;

    public WeChatWebAccessGrant(String accessToken, String openId) {
        super(accessToken);
        setOpenId(openId);
    }

    public WeChatWebAccessGrant(String accessToken, String scope, String refreshToken, Long expiresIn, String openId) {
        super(accessToken, scope, refreshToken, expiresIn);
        setOpenId(openId);
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
