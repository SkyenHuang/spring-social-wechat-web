package skyen.example.wechatofficialaccountsplatform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.stereotype.Component;


@Component
public class WechatOapOAuth2ServiceProvider extends AbstractOAuth2ServiceProvider<WechatOap> {


    @Autowired
    WechatOapUserContext wechatOapUserContext;

    public WechatOapOAuth2ServiceProvider(WechatOapOAuth2Template wechatOapOAuth2Template){
        super(wechatOapOAuth2Template);
    }

    @Override
    public WechatOap getApi(String accessToken) {
        return new WechatOapTemplate(accessToken, wechatOapUserContext.getOpenId().getValue());
    }
}
