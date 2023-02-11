package com.springcli.kafkaproducer.model;

import lombok.Data;

/**
 * @Author : haiyang.luo
 * @Date : 2023/2/9 18:02
 * @Description :
 */
@Data
public class MessageSendRequest {
    /**
     * token
     */
    private String token;

    /**
     * 消息
     */
    private String message;

    /**
     * topic
     */
    private String topic;
}
