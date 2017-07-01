package org.hp.config;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
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
    public DirectExchange getDirectExchange() {
        return new DirectExchange("direct");
    }


    @Bean
    public FanoutExchange getFanoutExchange() {
        return new FanoutExchange("fanout");
    }

    @Bean
    public TopicExchange getTopicExchange() {
        return new TopicExchange("topic");
    }


    @Bean
    public Queue getDirectQueue() {
        return new Queue("direct");
    }

    @Bean
    public Queue getTopicQueue() {
        return new Queue("topic");
    }

    @Bean
    public Queue getMessageQueue() {
        return new Queue("message");
    }

    @Bean
    public Queue getFanoutQueue() {
        return new Queue("fanout");
    }


    @Bean
    public Binding bindDirect() {
        return BindingBuilder.bind(getDirectQueue()).to(getDirectExchange()).with("direct");
    }

    @Bean
    public Binding bindTopic() {
        return BindingBuilder.bind(getTopicQueue()).to(getTopicExchange()).with("topic.#");
    }

    @Bean
    public Binding bindTopicMessage() {
        return BindingBuilder.bind(getMessageQueue()).to(getTopicExchange()).with("#.message");
    }


    @Bean
    public Binding bindFanoutMessage() {
        return BindingBuilder.bind(getFanoutQueue()).to(getFanoutExchange());
    }


    @Bean
    public SimpleMessageListenerContainer listenDirectQueue(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer listener = new SimpleMessageListenerContainer();
        listener.addQueueNames("direct");//这里可坚挺多个队列
        listener.setConcurrentConsumers(1);//并发数量
        listener.setConnectionFactory(connectionFactory);
        listener.setMessageListener(new ChannelAwareMessageListener() {

            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                byte[] body = message.getBody();
                String msg = new String(body,"utf-8");
                System.out.println("ConsumerA direct receive the message:" +msg);
            }
        });

        return listener;
    }

    @Bean
    public SimpleMessageListenerContainer listenTopicQueue(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer listener = new SimpleMessageListenerContainer();
        listener.addQueueNames("topic");//这里可坚挺多个队列
        listener.setConcurrentConsumers(1);//并发数量
        listener.setConnectionFactory(connectionFactory);
        listener.setMessageListener(new ChannelAwareMessageListener() {
            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                byte[] body = message.getBody();
                String msg = new String(body,"utf-8");
                System.out.println("ConsumerA topic receive the message:" +msg);
            }
        });

        return listener;
    }

    @Bean
    public SimpleMessageListenerContainer listenFanoutQueue(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer listener = new SimpleMessageListenerContainer();
        listener.addQueueNames("fanout");//这里可坚挺多个队列
        listener.setConnectionFactory(connectionFactory);
        listener.setMessageListener(new ChannelAwareMessageListener() {
            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                byte[] body = message.getBody();
                String msg = new String(body,"utf-8");
                System.out.println("ConsumerA fanout receive the message:" +msg);
            }
        });

        return listener;
    }

}
