package skyen.example.wechatofficialaccountsplatform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.social.support.ClientHttpRequestFactorySelector;
import org.springframework.social.support.FormMapHttpMessageConverter;
import org.springframework.social.support.LoggingErrorHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class WechatOapOAuth2Template implements OAuth2Operations {

    private final String appId = "wxa2ce967e92d4147c";

    private final String secret = "20d1590a5d65f70c62766eabbdd0ccac";

    private final String authorizeUrl = "https://open.weixin.qq.com/connect/oauth2/authorize"; //"?appid=wxf0e81c3bee622d60&redirect_uri=http%3A%2F%2Fnba.bluewebgame.com%2Foauth_response.php&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";

    private final String accessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token";//"?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

    private final String refreshTokenUrl = "https://api.weixin.qq.com/sns/oauth2/refresh_token";//"?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN"

    private RestTemplate restTemplate;

    @Autowired(required = false)
    private WechatOapUserContext wechatOapUserContext;

    @Override
    public String buildAuthorizeUrl(OAuth2Parameters parameters) {
        return buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE, parameters);
    }

    @Override
    public String buildAuthorizeUrl(GrantType grantType, OAuth2Parameters parameters) {
        if (grantType != GrantType.AUTHORIZATION_CODE) {
            throw new UnsupportedOperationException();
        }
        String url = UriComponentsBuilder.fromHttpUrl(authorizeUrl).queryParams(parameters)
                .queryParam("appid", appId)
                .queryParam("response_type", "code")
                .queryParam("scope", "snsapi_userinfo")
                .fragment("wechat_redirect").encode(Charset.forName("utf-8"))
                .build().toUriString();
        return url;
    }

    @Override
    public String buildAuthenticateUrl(OAuth2Parameters parameters) {
        return buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE, parameters);
    }

    @Override
    public String buildAuthenticateUrl(GrantType grantType, OAuth2Parameters parameters) {
        if (grantType != GrantType.AUTHORIZATION_CODE) {
            throw new UnsupportedOperationException();
        }
        String url = UriComponentsBuilder.fromHttpUrl(accessTokenUrl).queryParams(parameters)
                .queryParam("grant_type", "authorization_code").encode(Charset.forName("utf-8")).build(true).toUriString();
        return url;
    }

    @Override
    public AccessGrant exchangeForAccess(String authorizationCode, String redirectUri, MultiValueMap<String, String> additionalParameters) {
        LinkedMultiValueMap parameters = new LinkedMultiValueMap();
        parameters.add("appid", appId);
        parameters.add("secret", secret);
        parameters.add("grant_type", "authorization_code");
        parameters.add("code", authorizationCode);
        if (additionalParameters != null) {
            parameters.addAll(additionalParameters);
        }
        Map map = (Map) getRestTemplate().getForEntity(UriComponentsBuilder
                .fromHttpUrl(accessTokenUrl)
                .queryParams(parameters)
                .encode(Charset.forName("utf-8"))
                .build().toUriString(), Map.class).getBody();
        return createAccessGrant(map);
    }

    @Override
    public AccessGrant exchangeCredentialsForAccess(String username, String password, MultiValueMap<String, String> additionalParameters) {
        throw new UnsupportedOperationException();
    }

    @Override
    public AccessGrant refreshAccess(String refreshToken, MultiValueMap<String, String> additionalParameters) {
        LinkedMultiValueMap parameters = new LinkedMultiValueMap();
        parameters.add("appid", appId);
        parameters.add("refresh_token", refreshToken);
        parameters.add("grant_type", "refresh_token");
        parameters.addAll(additionalParameters);
        Map map = (Map) getRestTemplate().getForEntity(accessTokenUrl, Map.class, parameters).getBody();
        return createAccessGrant(map);
    }

    @Override
    public AccessGrant authenticateClient() {
        return authenticateClient(null);
    }

    @Override
    public AccessGrant authenticateClient(String scope) {
        throw new UnsupportedOperationException();
    }


    protected RestTemplate createRestTemplate() {
        ClientHttpRequestFactory requestFactory = ClientHttpRequestFactorySelector.getRequestFactory();
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>(2);
        converters.add(new FormHttpMessageConverter());
        converters.add(new FormMapHttpMessageConverter());
        converters.add(new MappingJackson2HttpMessageConverter() {

            @Override
            public List<MediaType> getSupportedMediaTypes() {
                if (super.getSupportedMediaTypes() != null) {
                    ArrayList<MediaType> mediaTypes = new ArrayList<>();
                    mediaTypes.addAll(super.getSupportedMediaTypes());
                    mediaTypes.add(MediaType.valueOf("text/plain"));
                    return mediaTypes;
                }
                return super.getSupportedMediaTypes();
            }
        });
        restTemplate.setMessageConverters(converters);
        restTemplate.setErrorHandler(new LoggingErrorHandler());
        return restTemplate;
    }

    protected RestTemplate getRestTemplate() {
        if (restTemplate == null) {
            restTemplate = createRestTemplate();
        }
        return restTemplate;
    }


    protected AccessGrant createAccessGrant(Map map) {
        wechatOapUserContext.getOpenId().setValue((String) map.get("openid"));
        AccessGrant accessGrant = new AccessGrant((String) map.get("access_token"), (String) map.get("scope"), (String) map.get("refresh_token"), Long.valueOf(String.valueOf(map.get("expires_in"))));
        return accessGrant;
    }
}
