package com.exciting.webapp.component;

import com.exciting.webapp.config.RabbitmqConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Date;


@SpringBootTest
@RunWith(SpringRunner.class)
class MsgReceiverTest {

    @Resource
    private RabbitTemplate rabbitTemplate;
    @Test
    void contextLoads() throws UnsupportedEncodingException {
        Message msg =
                MessageBuilder.withBody(("hello 江南一点雨"+new Date())
                        .getBytes(StandardCharsets.UTF_8))
                        .setHeader("x-delay", 3000)
                        .build();
        rabbitTemplate.convertAndSend(RabbitmqConfig.EXCHANGE_NAME, RabbitmqConfig.QUEUE_NAME, msg);
    }
}