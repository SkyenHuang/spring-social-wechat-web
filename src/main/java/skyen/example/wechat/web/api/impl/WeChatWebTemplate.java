package skyen.example.wechat.web.api.impl;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;
import skyen.example.wechat.web.api.OpenId;
import skyen.example.wechat.web.api.UserInfoOperations;
import skyen.example.wechat.web.api.WeChatWeb;

import java.util.ArrayList;
import java.util.List;

public class WeChatWebTemplate extends AbstractOAuth2ApiBinding implements WeChatWeb {

    private final OpenId openId;

    public OpenId getOpenId() {
        return openId;
    }

    UserInfoOperations userInfoOperations;

    public WeChatWebTemplate(String accessToken, OpenId openId) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.openId = openId;
        initUserInfoOperations();
    }

    void initUserInfoOperations() {
        userInfoOperations = new UserInfoTemplate(getRestTemplate(), getOpenId());
    }


    @Override
    protected MappingJackson2HttpMessageConverter getJsonMessageConverter() {
        return new MappingJackson2HttpMessageConverter() {

            @Override
            public List<MediaType> getSupportedMediaTypes() {
                List<MediaType> supportedMediaTypes = new ArrayList<>();
                supportedMediaTypes.addAll(super.getSupportedMediaTypes());
                supportedMediaTypes.add(MediaType.valueOf("text/plain"));
                return supportedMediaTypes;
            }
        };
    }

    @Override
    public UserInfoOperations userInfoOperations() {
        return userInfoOperations;
    }
}
