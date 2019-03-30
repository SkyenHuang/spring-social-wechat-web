package skyen.example.wechatofficialaccountsplatform;


import org.junit.Test;
import org.springframework.social.oauth2.OAuth2Parameters;

public class WechatOapTemplateTests {


    @Test
    public void buildUrl(){

        OAuth2Parameters parameters = new OAuth2Parameters();
        parameters.setScope("snsapi_userinfo");
        parameters.setRedirectUri("http://somewhere");
        parameters.setState("你好");
        String authorizeUrl = new WechatOapOAuth2Template().buildAuthorizeUrl(parameters);
        System.out.println(authorizeUrl);
    }

}
