package com.springbootcli.ssoauthclient01.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/member")
public class MemberController {

    /**
     * 会员列表页面
     */
    @RequestMapping("/list")
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView("member/list");
        return modelAndView;
    }

    /**
     * 详情
     */
    @PreAuthorize("hasAuthority('memberDetail')")
    @RequestMapping("/detail")
    public ModelAndView detail() {
        return new ModelAndView(" member/detail");
    }
}
