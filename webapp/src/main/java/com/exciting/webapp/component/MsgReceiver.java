package com.exciting.webapp.component;

import com.exciting.webapp.config.RabbitmqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MsgReceiver {
    @RabbitListener(queues = RabbitmqConfig.QUEUE_NAME)
    public void handleMsg(String msg) {
        log.info("handleMsg,{}",msg);
    }
}
