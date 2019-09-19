package skyen.example.wechat.web.connect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.stereotype.Component;
import skyen.example.wechat.web.api.impl.WeChatWebOAuth2Operations;
import skyen.example.wechat.web.api.WeChatWeb;
import skyen.example.wechat.web.api.impl.WeChatWebTemplate;


public class WeChatWebOAuth2ServiceProvider extends AbstractOAuth2ServiceProvider<WeChatWeb> {


    public WeChatWebOAuth2ServiceProvider(WeChatWebOAuth2Operations wechatWebOAuth2Operations){
        super(wechatWebOAuth2Operations);
    }

    public WeChatWebOAuth2ServiceProvider(String appId, String secret) {
        super(new WeChatWebOAuth2Operations(appId, secret));
    }


    @Override
    public WeChatWeb getApi(String accessToken) {
        return new WeChatWebTemplate(accessToken);
    }
}
