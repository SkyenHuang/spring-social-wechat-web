package skyen.example.wechat.web.connect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import skyen.example.wechat.web.api.OpenId;

@Component
@Scope(value= ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class WeChatWebUserContext {

    @Autowired
    private OpenId openId;

    public OpenId getOpenId() {
        return openId;
    }

    public void setOpenId(OpenId openId) {
        this.openId = openId;
    }
}
