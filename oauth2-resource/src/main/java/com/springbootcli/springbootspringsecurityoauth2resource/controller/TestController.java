package com.springbootcli.springbootspringsecurityoauth2resource.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lhy
 * @date 2021/7/3
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public String testSpringSecurityOauth2() {
        return "testSpringSecurityOauth2";
    }
}
