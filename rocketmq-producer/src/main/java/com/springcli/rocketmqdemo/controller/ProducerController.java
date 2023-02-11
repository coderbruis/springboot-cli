package com.springcli.rocketmqdemo.controller;

import com.springcli.rocketmqdemo.model.MessageSendRequest;
import com.springcli.rocketmqdemo.service.ProducerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @Author : haiyang.luo
 * @Date : 2023/2/9 15:07
 * @Description :
 */
@Controller
@RequestMapping
public class ProducerController {

    @Resource
    private ProducerService producerService;

    @PostMapping("sendMsg")
    public String sendMsg(@RequestBody MessageSendRequest messageSendRequest) {
        producerService.sendMsg(messageSendRequest.getMessage(), messageSendRequest.getTopic());
        return "ok";
    }
}
