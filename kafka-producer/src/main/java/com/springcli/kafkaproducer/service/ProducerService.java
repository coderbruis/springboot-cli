package com.springcli.kafkaproducer.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @Author : haiyang.luo
 * @Date : 2023/2/9 17:54
 * @Description :
 */
@Component
@Slf4j
public class ProducerService {

    @Resource
    private KafkaTemplate kafkaTemplate;

    public void sendMsg(String topic, String content) {
        String msgKey = UUID.randomUUID().toString();
        try {
            log.info("消息发送：{}", msgKey + content);
            kafkaTemplate.send(topic, msgKey + content);
        } catch (Exception e) {
            log.error("消息发送异常", e);
        }
    }
}
