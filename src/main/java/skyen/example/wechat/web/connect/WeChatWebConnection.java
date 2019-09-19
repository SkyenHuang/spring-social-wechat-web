package skyen.example.wechat.web.connect;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.support.OAuth2Connection;
import org.springframework.social.oauth2.OAuth2ServiceProvider;
import skyen.example.wechat.web.api.WeChatWeb;

public class WeChatWebConnection extends OAuth2Connection<WeChatWeb> {

    private String openId;


    public WeChatWebConnection(String providerId, String providerUserId, String accessToken, String refreshToken, Long expireTime, OAuth2ServiceProvider<WeChatWeb> serviceProvider, ApiAdapter<WeChatWeb> apiAdapter, String openId) {
        super(providerId, providerUserId, accessToken, refreshToken, expireTime, serviceProvider, apiAdapter);
        this.openId = openId;
    }

    public WeChatWebConnection(ConnectionData data, OAuth2ServiceProvider<WeChatWeb> serviceProvider, ApiAdapter<WeChatWeb> apiAdapter, String openId) {
        super(data, serviceProvider, apiAdapter);
        this.openId = openId;
    }
}
