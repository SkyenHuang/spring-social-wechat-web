package skyen.example.wechatofficialaccountsplatform;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

import java.util.Map;

public class WechatApiAdapter implements ApiAdapter<WechatOap> {
    @Override
    public boolean test(WechatOap api) {
        return true;
    }

    @Override
    public void setConnectionValues(WechatOap api, ConnectionValues values) {
        Map<String, Object> userInfo = api.getUserInfo();
        values.setDisplayName((String) userInfo.get("nickname"));
    }

    @Override
    public UserProfile fetchUserProfile(WechatOap api) {
        Map<String, Object> userInfo = api.getUserInfo();
        new WechatOapUserProfile((String) userInfo.get("openid"), (String) userInfo.get("nickname"), "", "", "", (String) userInfo.get("nickname"));
        return null;
    }

    @Override
    public void updateStatus(WechatOap api, String message) {

    }
}
