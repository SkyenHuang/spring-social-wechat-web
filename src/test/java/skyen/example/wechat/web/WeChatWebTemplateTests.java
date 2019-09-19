package skyen.example.wechat.web;


import org.junit.Test;
import org.springframework.social.oauth2.OAuth2Parameters;
import skyen.example.wechat.web.api.impl.WeChatWebOAuth2Operations;

public class WeChatWebTemplateTests {


    @Test
    public void buildUrl(){

        OAuth2Parameters parameters = new OAuth2Parameters();
        parameters.setScope("snsapi_userinfo");
        parameters.setRedirectUri("http://somewhere");
        parameters.setState("你好");
        String authorizeUrl = new WeChatWebOAuth2Operations(appId, secret, authorizeUrl, accessTokenUrl, refreshTokenUrl).buildAuthorizeUrl(parameters);
        System.out.println(authorizeUrl);
    }

}
