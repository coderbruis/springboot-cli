package com.springcli.rocketmqdemo.model;

import lombok.Data;

/**
 * @Author : haiyang.luo
 * @Date : 2023/2/9 15:19
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
