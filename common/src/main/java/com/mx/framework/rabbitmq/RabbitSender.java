package com.mx.framework.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * @author : ShangGuanMingPeng
 * Description :
 * Date :Create in ${date} ${time}
 * Modified By :
 */
@Component
@Slf4j
public class RabbitSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private final RabbitTemplate.ConfirmCallback confirmCallback = (correlationData, ack, cause) -> {
        log.info("correlationData:{}",correlationData);
        log.info("ack:{}",ack);
        if(!ack){
            log.info("异常处理");
        }
    };

    private final RabbitTemplate.ReturnCallback returnCallback = (message, replyCode, replyText, exchange, routingKey) -> {
        log.info("message:{}",message);
        log.info("replyCode:{}",replyCode);
        log.info("replyText:{}",replyText);
        log.info("exchange:{}",exchange);
        log.info("routingKey:{}",routingKey);
    };

    public void send(Object message, Map<String,Object> properties){
        MessageHeaders messageHeaders = new MessageHeaders(properties);
        Message msg = MessageBuilder.createMessage(message, messageHeaders);
        rabbitTemplate.setReturnCallback(returnCallback);
        rabbitTemplate.setConfirmCallback(confirmCallback);
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(String.valueOf(new Date().getTime()));
        rabbitTemplate.convertAndSend("exchange-1","springboot.hello", msg,correlationData);
    }
}
