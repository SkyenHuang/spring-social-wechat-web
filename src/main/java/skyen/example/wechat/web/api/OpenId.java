package skyen.example.wechat.web.api;

import java.io.Serializable;

public class OpenId implements Serializable {

    String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
