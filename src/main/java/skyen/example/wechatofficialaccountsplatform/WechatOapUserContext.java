package skyen.example.wechatofficialaccountsplatform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value= ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class WechatOapUserContext {

    @Autowired
    private OpenId openId;

    public OpenId getOpenId() {
        return openId;
    }

    public void setOpenId(OpenId openId) {
        this.openId = openId;
    }
}
