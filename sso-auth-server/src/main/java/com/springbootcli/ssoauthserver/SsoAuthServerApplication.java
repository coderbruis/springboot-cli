package com.springbootcli.ssoauthserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.springbootcli.ssoauthserver.mapper")
public class SsoAuthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsoAuthServerApplication.class, args);
    }

}
