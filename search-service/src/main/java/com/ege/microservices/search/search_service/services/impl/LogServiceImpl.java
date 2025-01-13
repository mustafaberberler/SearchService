package com.ege.microservices.search.search_service.services.impl;

import com.ege.microservices.search.search_service.services.LogService;
import com.ege.microservices.search.search_service.services.dtos.rabbitdtos.LogMessageDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class LogServiceImpl implements LogService {

    private final RabbitTemplate rabbitTemplate;

    @Value("${sample.rabbitmq.log.exchange}")
    private String logExchange;

    @Value("${sample.rabbitmq.log.routingKey}")
    private String logRoutingKey;

    @Override
    public void sendLog(String level, String message, String serviceName) {
        LogMessageDTO logMessageDTO = new LogMessageDTO(level, message, serviceName, System.currentTimeMillis());
        rabbitTemplate.convertAndSend(logExchange, logRoutingKey, logMessageDTO);
        log.info("Log sent to Logging Service: {}", logMessageDTO);
    }


}
