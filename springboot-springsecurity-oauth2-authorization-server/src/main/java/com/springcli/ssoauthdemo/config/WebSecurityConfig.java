package com.springcli.ssoauthdemo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author lhy
 * @date 2021/7/3
 */
@Order(2)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 用户密码、权限校验
     */
    @Resource
    private UserDetailsService userDetailsService;

    /**
     * 嵌入自定义userDetailsService
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    /**
     * 安全拦截机制
     *
     * Tips: 如果对于认证服务器需要开启Authorization_code模式认证，则需要将configure(HttpSecurity http)给禁掉？？目前为止是这样的，还没有深入去研究。
     *
     * 而如果想直接访问认证服务器上的swagger，则需要先approve过后才能访问。
     *
     * 如果放开configure(HttpSecurity http)，则是无法进入Authorization_code模式的。
     *
     * @throws Exception
     */
//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.csrf().disable()
//                .requestMatchers().anyRequest()
//                .and()
//                .authorizeRequests()
//                // /oauth开头的请求不需要认证授权
//                .antMatchers("/oauth/**").permitAll()
//                .and()
//                .exceptionHandling().accessDeniedHandler(null);
//    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.formLogin()
//                .loginPage("/login")
//                .and()
//                .authorizeRequests()
//                .antMatchers("/login").permitAll()
//                .anyRequest()
//                .authenticated()
//                .and().csrf().disable().cors();
//    }

    /**
     * 将 check_token 暴露出去，否则资源服务器访问时报403错误
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/oauth/check_token");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 将认证管理器注入SpringIOC容器中，用于密码模式
     * @return
     * @throws Exception
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * @Primary表示的是dataSource的bean采用DataSourceBuilder.create().build()返回的实例。
     * @return
     */
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        // 配置数据源（注意，我使用的是 HikariCP 连接池），以上注解是指定数据源，否则会有冲突
        return DataSourceBuilder.create().build();
    }
}
