package skyen.example.wechat.web.api.impl;

import org.springframework.web.client.RestTemplate;
import skyen.example.wechat.web.api.OpenId;

public abstract class AbstractWeChatWebOperations {

    private final OpenId openId;

    private final RestTemplate restTemplate;

    protected AbstractWeChatWebOperations(RestTemplate restTemplate, OpenId openId) {
        this.restTemplate = restTemplate;
        this.openId = openId;
    }

    public OpenId getOpenId() {
        return openId;
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }
}
