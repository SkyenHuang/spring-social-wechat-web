package skyen.example.wechat.web.connect;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import skyen.example.wechat.web.api.WeChatWeb;
import skyen.example.wechat.web.api.WeChatWebUserProfile;

import java.util.Map;

public class WeChatWebApiAdapter implements ApiAdapter<WeChatWeb> {
    @Override
    public boolean test(WeChatWeb api) {
        return true;
    }

    @Override
    public void setConnectionValues(WeChatWeb api, ConnectionValues values) {
        Map<String, Object> userInfo = api.userInfoOperations().getUserInfo();
        values.setDisplayName((String) userInfo.get("nickname"));
        values.setImageUrl((String) userInfo.get("headimgurl"));
        values.setProviderUserId((String) userInfo.get("openid"));
    }

    @Override
    public UserProfile fetchUserProfile(WeChatWeb api) {
        Map<String, Object> userInfo = api.userInfoOperations().getUserInfo();
        return new WeChatWebUserProfile((String) userInfo.get("openid"), (String) userInfo.get("nickname"), "", "", "", (String) userInfo.get("nickname"));
    }

    @Override
    public void updateStatus(WeChatWeb api, String message) {

    }
}
