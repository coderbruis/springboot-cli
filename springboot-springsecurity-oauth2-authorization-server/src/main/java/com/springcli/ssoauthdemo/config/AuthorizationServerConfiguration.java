package com.springcli.ssoauthdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.UUID;

/**
 * 开启认证服务器
 * @author lhy
 * @date 2021/7/3
 */
@Configuration
@EnableAuthorizationServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    /**
     * 设置jwt加密key
     */
    private static final String JWT_SIGNING_KEY = "jwt_MC43A6m0Xt9jUIV";

    /**
     * 认证管理器，在WebSecurityConfig里配置了
     */
    @Resource
    private AuthenticationManager authenticationManager;

    /**
     * 自定义用户服务
     */
    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private RedisConnectionFactory redisConnectionFactory;

    @Resource
    private DataSource dataSource;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 从数据库中读取客户端配置
        clients.withClientDetails(jdbcClientDetailsService());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore())
                // 配置密码模式需要制定认证器
                .authenticationManager(authenticationManager)
                .accessTokenConverter(accessTokenConverter())
                // 支持GET  POST  请求获取token
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
                // 必须注入userDetailsService否则根据refresh_token无法加载用户信息
                .userDetailsService(userDetailsService)
                // .exceptionTranslator(customWebResponseExceptionTranslator)
                // 开启刷新token
                .reuseRefreshTokens(true);
    }

    /**
     * 认证服务器的安全配置
     *
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .tokenKeyAccess("permitAll()")
                //isAuthenticated():排除anonymous   isFullyAuthenticated():排除anonymous以及remember-me
                .checkTokenAccess("isAuthenticated()")
                //允许表单认证
                .allowFormAuthenticationForClients();
    }

    /**
     * jwt令牌增强，添加加密key
     */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        // 使用OAuth2默认的JwtAccessTokenConverter
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        // 密钥加强
        converter.setSigningKey(JWT_SIGNING_KEY);
        return converter;
    }

    /**
     * 使用JWT存储令牌信息
     * @return
     */
    @Bean
    public TokenStore tokenStore() {
        RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
        // 解决每次生成的 token都一样的问题
        redisTokenStore.setAuthenticationKeyGenerator(oAuth2Authentication -> UUID.randomUUID().toString());
        return redisTokenStore;
    }

    /**
     * token认证服务
     */
    @Bean
    public ResourceServerTokenServices tokenService() {
        // 授权服务和资源服务在统一项目内，可以使用本地认证方式，如果再不同工程，需要使用远程认证方式
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        return defaultTokenServices;
    }

    @Bean
    public ClientDetailsService jdbcClientDetailsService() {
        // 基于JDBC实现，需要实现在数据库配置客户端信息以及密码加密方式
        JdbcClientDetailsService detailsService = new JdbcClientDetailsService(dataSource);
        detailsService.setPasswordEncoder(passwordEncoder);
        return detailsService;
    }
}


