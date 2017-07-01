package org.hp.ctrl;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hpym365@gmail.com
 * @Description
 * @Date 17-7-1 上午7:25
 */
@RestController
public class ProduceController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @RequestMapping("/direct")
    public String direct(){
        rabbitTemplate.convertAndSend("direct","direct","directMessage");
        return "send message to direct exchange with routingkey direct";
    }


    @RequestMapping("/topic")
    public String topic(){
        rabbitTemplate.convertAndSend("topic","topic","topicMessage");
        return "send message to topic exchange with routingkey topic.message";
    }

    @RequestMapping("/fanout")
    public String fanout(){
        rabbitTemplate.convertAndSend("fanout","fanout","fanoutMessage");
        return "send message to fanout exchange with routingkey fanout";
    }


}
