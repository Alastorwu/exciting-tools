package com.exciting.webapp.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitmqConfig {

    public static final String QUEUE_NAME = "javaboy_delay_queue";
    public static final String EXCHANGE_NAME = "javaboy_delay_exchange";
    public static final String EXCHANGE_TYPE = "x-delayed-message";

    @Bean
    Queue queue() {
        return new Queue(QUEUE_NAME, true, false, false);
    }

    @Bean
    CustomExchange customExchange() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        return new CustomExchange(EXCHANGE_NAME, EXCHANGE_TYPE, true, false,args);
    }

    @Bean
    Binding binding() {
        return BindingBuilder.bind(queue())
                .to(customExchange()).with(QUEUE_NAME).noargs();
    }


}
