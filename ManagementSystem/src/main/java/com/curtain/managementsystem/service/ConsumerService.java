package com.curtain.managementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class ConsumerService {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    // 使用JmsListener配置消费者监听的队列，其中name是接收到的消息
    @JmsListener(destination = "ActiveMQQueue")
    // SendTo 会将此方法返回的数据, 写入到 OutQueue 中去.
    @SendTo("SQueue")
    public String handleMessage(String name) throws Exception {
        System.out.println("接受开始----:" + name);
        Thread.sleep(500);
        System.out.println("成功接受Name:" + name);
        return "成功接受Name:" + name;
    }
}
