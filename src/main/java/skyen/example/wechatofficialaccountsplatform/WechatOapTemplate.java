package skyen.example.wechatofficialaccountsplatform;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WechatOapTemplate extends AbstractOAuth2ApiBinding implements WechatOap {

    private final String openId;

    public WechatOapTemplate(String accessToken, String openId) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.openId = openId;
    }

    public Map<String, Object> getUserInfo() {
        return getRestTemplate().getForEntity("https://api.weixin.qq.com/sns/userinfo?openid={openId}&lang=zh_CN", Map.class,openId).getBody();
    }


    @Override
    protected MappingJackson2HttpMessageConverter getJsonMessageConverter() {
        return new MappingJackson2HttpMessageConverter(){

            @Override
            public List<MediaType> getSupportedMediaTypes() {
                List<MediaType> supportedMediaTypes = new ArrayList<>();
                supportedMediaTypes.addAll(super.getSupportedMediaTypes());
                supportedMediaTypes.add(MediaType.valueOf("text/plain"));
                return supportedMediaTypes;
            }
        };
    }
}
