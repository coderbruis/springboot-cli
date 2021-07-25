package com.springcli.rocketmqconsumer.mq;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * @author lhy
 * @date 2021/7/25
 */
@Service
@RocketMQMessageListener(topic = "${springboot.cli.rocketmq-consume.topic}",
        consumerGroup = "${spirngboot.cli.rocketmq-consume.group}",
        nameServer = "${spirngboot.cli.rocketmq-consume.namesrv.address}")
public class ConsumerDemo implements RocketMQListener<String> {

    @Override
    public void onMessage(String s) {
        System.out.println("收到消息了，内容是：" + s);
    }
}
