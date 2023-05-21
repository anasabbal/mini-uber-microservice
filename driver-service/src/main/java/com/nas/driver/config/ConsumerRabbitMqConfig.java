package com.nas.driver.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;




@Slf4j
@Configuration
public class ConsumerRabbitMqConfig {
    /*


    @Value("${spring.rabbitmq.queue}")
    private String queue;

    @Value("${spring.rabbitmq.template.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.template.routing-key}")
    private String routingKey;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Bean
    Queue queue() {
        var q = new Queue(queue, true);
        log.info("[+] Queue with payload {} created !!!!", JSONUtil.toJSON(q));
        return q;
    }
    @Bean
    Exchange myExchange() {
        var newExchange = ExchangeBuilder.directExchange(exchange).durable(true).build();
        log.info("[+] Exchange with payload {} created !!!", JSONUtil.toJSON(newExchange));
        return newExchange;
    }

    @Bean
    Binding binding() {
        var bind = BindingBuilder
                .bind(queue())
                .to(myExchange())
                .with(routingKey)
                .noargs();
        log.info("[+] Binding Builder with payload {} created !!", JSONUtil.toJSON(bind));
        return bind;
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(host);
        cachingConnectionFactory.setUsername(username);
        cachingConnectionFactory.setPassword(password);
        return cachingConnectionFactory;
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }*/
}
