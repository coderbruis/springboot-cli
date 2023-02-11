package com.springcli.kafkaproducer.controller;

import com.springcli.kafkaproducer.model.MessageSendRequest;
import com.springcli.kafkaproducer.service.ProducerService;
import org.springframework.boot.autoconfigure.data.ConditionalOnRepositoryType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;

/**
 * @Author : haiyang.luo
 * @Date : 2023/2/9 17:54
 * @Description :
 */
@Controller
public class ProducerController {

    @Resource
    private ProducerService producerService;

    @PostMapping("sendMsg")
    public String sendMsg(@RequestBody MessageSendRequest messageSendRequest) {
        producerService.sendMsg(messageSendRequest.getTopic(), messageSendRequest.getMessage());
        return "ok";
    }
}
