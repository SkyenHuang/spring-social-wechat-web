package skyen.example.wechat.web.api.impl;

import org.springframework.web.client.RestTemplate;
import skyen.example.wechat.web.api.OpenId;
import skyen.example.wechat.web.api.UserInfoOperations;

import java.util.Map;

public class UserInfoTemplate extends AbstractWeChatWebOperations implements UserInfoOperations {


    public UserInfoTemplate(RestTemplate restTemplate, OpenId openId) {
        super(restTemplate, openId);
    }

    public Map<String, Object> getUserInfo() {
        return getRestTemplate().getForEntity("https://api.weixin.qq.com/sns/userinfo?openid={openId}&lang=zh_CN", Map.class, getOpenId().getValue()).getBody();
    }

}
