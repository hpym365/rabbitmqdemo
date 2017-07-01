package org.hp.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hpym365@gmail.com
 * @Description
 * @Date 17-7-1 上午7:22
 */
@Configuration
public class RabbitMQConfig {

    @Bean
    public DirectExchange getDirectExchange(){
        return new DirectExchange("direct");
    }


    @Bean
    public FanoutExchange getFanoutExchange(){
        return new FanoutExchange("fanout");
    }

    @Bean
    public TopicExchange getTopicExchange(){
        return new TopicExchange("topic");
    }
}
