package com.curtain.managementsystem.controller;

import javax.jms.DeliveryMode;
import javax.jms.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ProviderController {

    //注入存放消息的队列，用于下列方法一
    @Autowired
    private Queue queue;

    //注入springboot封装的工具类
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @RequestMapping("send")
    public void send(String name) {
        long oldTime = System.currentTimeMillis();
        //方法一：添加消息到消息队列
        jmsMessagingTemplate.convertAndSend(queue, name);
        //方法二：这种方式不需要手动创建queue，系统会自行创建名为test的队列
        //jmsMessagingTemplate.convertAndSend("test", name);
        for(int i=0;i<1000;i++){
            long curTime = System.currentTimeMillis();
            jmsMessagingTemplate.convertAndSend(queue, "msg_"+i+"_"+curTime);
        }
        JmsTemplate jmsTemplate = jmsMessagingTemplate.getJmsTemplate();
        jmsTemplate.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        jmsTemplate.setExplicitQosEnabled(true);
        jmsMessagingTemplate.setJmsTemplate(jmsTemplate);
        System.out.println("当前发送消息模式："+jmsMessagingTemplate.getJmsTemplate().getDeliveryMode());
        for(int i=1000;i<2000;i++){
            long curTime = System.currentTimeMillis();
            jmsMessagingTemplate.convertAndSend(queue, "msg_"+i+"_"+curTime);
        }
        System.out.println("发送ActiveMQ消息总耗时："+(System.currentTimeMillis()-oldTime)+"毫秒");
    }
}