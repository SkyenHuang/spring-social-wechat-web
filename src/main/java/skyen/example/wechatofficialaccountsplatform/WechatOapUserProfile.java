package skyen.example.wechatofficialaccountsplatform;

import org.springframework.social.connect.UserProfile;

import java.util.List;

public class WechatOapUserProfile extends UserProfile {

//    {
//        "openid":" OPENID",
//            " nickname": NICKNAME,
//            "sex":"1",
//            "province":"PROVINCE"
//        "city":"CITY",
//            "country":"COUNTRY",
//            "headimgurl":       "http://thirdwx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/46",
//            "privilege":[ "PRIVILEGE1" "PRIVILEGE2"     ],
//        "unionid": "o6_bmasdasdsad6_2sgVt7hMZOPfL"
//    }

    private String openId;
    private String nickname;
    private String sex;
    private String province;
    private String country;
    private String headImgUrl;
    private List<String> privilege;
    private String unionId;

    public WechatOapUserProfile(String id, String name, String firstName, String lastName, String email, String username) {
        super(id, name, firstName, lastName, email, username);
    }
}
