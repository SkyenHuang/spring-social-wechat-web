package skyen.example.wechatofficialaccountsplatform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.social.security.SpringSocialConfigurer;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

@RestController
@SpringBootApplication
public class WechatOfficalAccountsPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(WechatOfficalAccountsPlatformApplication.class, args);
    }

    @RequestMapping("/")
    public Object index(@Autowired Authentication object) {
        return object;
    }

    @Configuration
    @EnableWebSecurity
    public static class SecurityConfig extends WebSecurityConfigurerAdapter {


        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests().antMatchers("/login", "/auth/**").permitAll()
                    .anyRequest().authenticated().and().formLogin().and()
                    .apply(new SpringSocialConfigurer());
        }

        @Bean
        public SocialUserDetailsService socialUsersDetailService() {
            return new SimpleSocialUsersDetailService(userDetailsService());
        }

    }

    @Bean
    public ConnectController connectController(ConnectionFactoryLocator connectionFactoryLocator, ConnectionRepository connectionRepository) {
        ConnectController connectController = new ConnectController(connectionFactoryLocator, connectionRepository);
        return connectController;
    }


}
