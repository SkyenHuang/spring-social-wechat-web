package skyen.example.wechat.web.connect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.support.OAuth2Connection;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.util.Assert;
import skyen.example.wechat.web.api.WeChatWeb;


public class WeChatWebConnectionFactory extends OAuth2ConnectionFactory<WeChatWeb> {


    public WeChatWebConnectionFactory(String appid, String secret) {
        super("wechatweb", new WeChatWebOAuth2ServiceProvider(appid, secret), new WeChatWebApiAdapter());
    }


    @Override
    public Connection<WeChatWeb> createConnection(AccessGrant accessGrant) {
        Assert.isInstanceOf(WeChatWebAccessGrant.class, accessGrant);
        WeChatWebAccessGrant weChatWebAccessGrant = (WeChatWebAccessGrant) accessGrant;
        return new WeChatWebConnection(getProviderId(), extractProviderUserId(accessGrant), accessGrant.getAccessToken(),
                accessGrant.getRefreshToken(), accessGrant.getExpireTime(), getWeChatWebOAuth2ServiceProvider(), getApiAdapter(), weChatWebAccessGrant.getOpenId());
    }

    private WeChatWebOAuth2ServiceProvider getWeChatWebOAuth2ServiceProvider() {
        return (WeChatWebOAuth2ServiceProvider) getServiceProvider();
    }

    @Override
    public Connection<WeChatWeb> createConnection(ConnectionData data) {
        return super.createConnection(data);
    }

    @Override
    protected String extractProviderUserId(AccessGrant accessGrant) {
        // TODO: 2019/8/3 get openid
        return null;
    }
}
