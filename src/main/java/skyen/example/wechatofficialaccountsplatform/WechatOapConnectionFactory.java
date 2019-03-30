package skyen.example.wechatofficialaccountsplatform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.stereotype.Component;

@Component
public class WechatOapConnectionFactory extends OAuth2ConnectionFactory<WechatOap> {

    @Autowired(required = false)
    WechatOapUserContext wechatOapUserContext;

    public WechatOapConnectionFactory(WechatOapOAuth2ServiceProvider wechatOapOAuth2ServiceProvider) {
        super("wechatoap", wechatOapOAuth2ServiceProvider, new WechatApiAdapter());
    }


    @Override
    public Connection<WechatOap> createConnection(AccessGrant accessGrant) {
        return super.createConnection(accessGrant);
    }

    @Override
    public Connection<WechatOap> createConnection(ConnectionData data) {
        return super.createConnection(data);
    }

    @Override
    protected String extractProviderUserId(AccessGrant accessGrant) {
        return wechatOapUserContext.getOpenId().getValue();
    }
}
