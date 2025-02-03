package com.ege.microservices.search.search_service.utils.rabbitutils;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

//    @Value("${sample.rabbitmq.log.queue}")
//    private String logQueueName;
//
//    @Value("${sample.rabbitmq.log.exchange}")
//    private String logExchange;
//
//    @Value("${sample.rabbitmq.log.routingKey}")
//    private String logRoutingKey;
//
//    // Logging için Exchange ve Queue
//    @Bean
//    DirectExchange logExchange() {
//        return new DirectExchange(logExchange);
//    }
//
//    @Bean
//    Queue logQueue() {
//        return new Queue(logQueueName, true);
//    }
//
//    @Bean
//    Binding logBinding(Queue logQueue, DirectExchange logExchange) {
//        return BindingBuilder.bind(logQueue).to(logExchange).with(logRoutingKey);
//    }
//
//    @Bean
//    public MessageConverter jsonMessageConverter(){
//        return new Jackson2JsonMessageConverter();
//    }
//
//    @Bean
//    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
//        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//        factory.setConnectionFactory(connectionFactory);
//        factory.setMessageConverter(jsonMessageConverter());
//        return factory;
//    }

    /// 03.02.2025
    public static final String logQueueName = "log_service_queue";

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public SimpleMessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory) {
        return new SimpleMessageListenerContainer(connectionFactory);
    }

    @Bean
    public Queue logQueue() {
        return new Queue(logQueueName, true); // durable=true
    }

}
