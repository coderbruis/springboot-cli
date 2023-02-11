package com.springcli.kafkaconsumer.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @Author : haiyang.luo
 * @Date : 2023/2/9 18:16
 * @Description :
 */
@Service
@Slf4j
@ConditionalOnProperty(value = "spring.profiles.active", havingValue = "kafka")
public class kafkaConsumer {

    @KafkaListener(topics = {"kafka_test_topic"}, groupId = "${spring.kafka.consumer.group-id}")
    public void onMessage(ConsumerRecord<?, ?> consumerRecord, Acknowledgment ack) {
        //消费者必须手动调用ack.acknowledge();不然会重复消费 因为在yml中配置了
        //ack-mode: manual_immediate
        ack.acknowledge();
        Optional<?> optional = Optional.ofNullable(consumerRecord.value());
        if (optional.isPresent()) {
            Object msg = optional.get();
            log.info("消费者接受消息：{}", msg);
        }
    }
}
