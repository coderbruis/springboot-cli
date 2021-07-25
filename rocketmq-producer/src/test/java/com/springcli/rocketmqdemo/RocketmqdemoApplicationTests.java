package com.springcli.rocketmqdemo;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
class RocketmqdemoApplicationTests {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @Value("${springboot.cli.rocketmq-producer.topic}")
    private String topicName;

    @Value("${rocketmq.name-server}")
    private String nameServer;

    @Test
    void contextLoads() {
        String test = "Hello SpringBoot-cli RocketMQ";
        rocketMQTemplate.convertAndSend(topicName, test);
//        rocketMQTemplate.send();
    }
}
