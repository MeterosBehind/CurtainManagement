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
//    @JmsListener(destination = "ActiveMQQueue")
    // SendTo 会将此方法返回的数据, 写入到 OutQueue 中去.
    @SendTo("SQueue")
    public String handleMessage(String name) throws Exception {

        long oldTime = System.currentTimeMillis();
        if("msg_1".equals(name)){
            System.out.println("开始时间："+System.currentTimeMillis());
        } else if ("msg_50000".equals(name)) {
            System.out.println("结束时间："+System.currentTimeMillis());
        }
//        System.out.println("每次耗时："+(System.currentTimeMillis()-oldTime)+"毫秒");
//        System.out.println("成功接受数据，线程：" +Thread.currentThread().getName() +"，名称："+ name);
        return "成功接受Name:" + name;
    }

//    @JmsListener(destination = "ActiveMQQueue")
    @SendTo("SQueue1")
    public String handelMessage(String name){
        return "2----成功接受Name:" + name;
    }
}
