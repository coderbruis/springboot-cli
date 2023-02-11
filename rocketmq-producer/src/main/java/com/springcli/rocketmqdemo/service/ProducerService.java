package com.springcli.rocketmqdemo.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @Author : haiyang.luo
 * @Date : 2023/2/9 15:08
 * @Description :
 */
@Component
@Slf4j
public class ProducerService {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    public void sendMsg(String content, String topic) {
        String msgKey = UUID.randomUUID().toString();
        Message<String> message = MessageBuilder.withPayload(msgKey + content).setHeader("KEYS", msgKey).build();
        log.info(String.format("消息发送：%s", message));
        rocketMQTemplate.sendOneWay(topic, content);
    }
}
