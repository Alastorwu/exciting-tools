package com.exciting.webapp.controller;

import com.exciting.common.entity.ResEntity;
import com.exciting.common.entity.vo.ExcitingVo;
import com.exciting.service.ExcitingService;
import com.exciting.webapp.config.RabbitmqConfig;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.Date;

@Api(tags ={"Rabbitmq操作"})
@Slf4j
@RestController
public class RabbitController {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @RequestMapping(value = "/contextLoads",method = {RequestMethod.POST})
    public ResEntity<Boolean> contextLoads() throws UnsupportedEncodingException {
        Message msg =
                MessageBuilder.withBody(("hello 江南一点雨"+new Date())
                                .getBytes("UTF-8"))
                        .setHeader("x-delay", 3000)
                        .build();
        rabbitTemplate.convertAndSend(
                RabbitmqConfig.EXCHANGE_NAME, RabbitmqConfig.QUEUE_NAME, msg);
        return ResEntity.ok(true);
    }



}
