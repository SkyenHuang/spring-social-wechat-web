package skyen.example.wechatofficialaccountsplatform;

import org.springframework.social.ApiBinding;

import java.util.Map;

public interface WechatOap extends ApiBinding {

    Map<String, Object> getUserInfo();
}
