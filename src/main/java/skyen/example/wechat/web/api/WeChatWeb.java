package skyen.example.wechat.web.api;

import org.springframework.social.ApiBinding;

public interface WeChatWeb extends ApiBinding {

    UserInfoOperations userInfoOperations();
}
