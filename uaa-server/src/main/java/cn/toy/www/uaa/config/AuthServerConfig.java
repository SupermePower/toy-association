package cn.toy.www.uaa.config;

import cn.toy.www.Constant;
import cn.toy.www.uaa.security.CustomerAccessTokenConverter;
import cn.toy.www.uaa.security.QrCodeVerificationTokenGranter;
import cn.toy.www.uaa.service.CustomerUserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Configuration
@Order(1)
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        /*
         * 配置客户端详情信息(Client Details)
         * clientId：（必须的）用来标识客户的Id。
         * secret：（需要值得信任的客户端）客户端安全码，如果有的话。
         * scope：用来限制客户端的访问范围，如果为空（默认）的话，那么客户端拥有全部的访问范围。
         * authorizedGrantTypes：此客户端可以使用的授权类型，默认为空。
         * authorities：此客户端可以使用的权限（基于Spring Security authorities）。
         */
        clients.inMemory()
                .withClient(Constant.ServiceClient.CLIENT_ID)
                .secret(Constant.ServiceClient.CLIENT_SECRET)
                .scopes(Constant.ServiceClient.SCOPE)
                .authorizedGrantTypes("implicit", "refresh_token", "password", "authorization_code", "qr_code")
                // 24小时过期
                .accessTokenValiditySeconds(24 * 3600)
                .refreshTokenValiditySeconds(24 * 3600 * 10)
        ;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore())
                .reuseRefreshTokens(false)
                .tokenEnhancer(jwtTokenEnhancer())
                .authenticationManager(authenticationManager)
                .userDetailsService(customerUserDetailService);
        endpoints.tokenGranter(tokenGranter(endpoints));
    }

    private TokenGranter tokenGranter(final AuthorizationServerEndpointsConfigurer endpoints) {
        List<TokenGranter> granters = new ArrayList<>(Collections.singletonList(endpoints.getTokenGranter()));
        granters.add(new QrCodeVerificationTokenGranter(authenticationManager, endpoints.getTokenServices(), endpoints.getClientDetailsService(),
                endpoints.getOAuth2RequestFactory()));
        return new CompositeTokenGranter(granters);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients();
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtTokenEnhancer());
    }

    @Bean
    protected JwtAccessTokenConverter jwtTokenEnhancer() {
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("xmx-jwt.jks"), "xmx1234".toCharArray());
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("xmx-jwt"));
        converter.setAccessTokenConverter(new CustomerAccessTokenConverter(customerUserDetailService));
        return converter;
    }

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomerUserDetailServiceImpl customerUserDetailService;
}
