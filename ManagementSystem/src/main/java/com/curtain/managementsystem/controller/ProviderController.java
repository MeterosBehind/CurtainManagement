package com.curtain.managementsystem.controller;

import javax.jms.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.transaction.annotation.Transactional;
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
        for(int i=1;i<=1000;i++){
            long curTime = System.currentTimeMillis();
            jmsMessagingTemplate.convertAndSend(queue, "msg_"+i+"_"+curTime);
        }
        JmsTemplate jmsTemplate = jmsMessagingTemplate.getJmsTemplate();
        jmsTemplate.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        jmsTemplate.setExplicitQosEnabled(true);
        jmsTemplate.setSessionTransacted(true);
        jmsMessagingTemplate.setJmsTemplate(jmsTemplate);
        System.out.println("当前发送消息模式(持久性/非持久性)："+jmsMessagingTemplate.getJmsTemplate().getDeliveryMode());
        System.out.println("当前发送消息模式(事务/非事务)："+jmsMessagingTemplate.getJmsTemplate().getSessionAcknowledgeMode());
        for(int i=1000;i<2000;i++){
            jmsMessagingTemplate.convertAndSend(queue, "msg_"+i);
        }
        System.out.println("发送ActiveMQ消息总耗时："+(System.currentTimeMillis()-oldTime)+"毫秒");
    }


    @RequestMapping("sendNormal")
    public void sendNormal(String name) {
        long oldTime = System.currentTimeMillis();
        for(int i=1;i<=100000;i++){
            jmsMessagingTemplate.convertAndSend(queue, "msg_"+i);
        }
        System.out.println("发送ActiveMQ消息总耗时："+(System.currentTimeMillis()-oldTime)+"毫秒");
    }

    @RequestMapping("sendNormal4ZK")
    public void sendNormal4ZK(String name) {
        long oldTime = System.currentTimeMillis();
        for(int i=1;i<=50000;i++){
            jmsMessagingTemplate.convertAndSend(queue, "msg_"+i);
        }
        System.out.println("发送ActiveMQ消息总耗时："+(System.currentTimeMillis()-oldTime)+"毫秒");
    }

    @RequestMapping("sendBusiness")
    @Transactional
    public void sendNew(String name) {
        //获取连接工厂
        ConnectionFactory connectionFactory = jmsMessagingTemplate.getConnectionFactory();
        Session session = null;
        try{
            long oldTime = System.currentTimeMillis();
            //创建连接
            Connection connection = connectionFactory.createConnection();
            //参数一：是否开启消息事务
            session = connection.createSession(true,Session.AUTO_ACKNOWLEDGE);
            //创建生产者
            MessageProducer producer = session.createProducer(queue);

            for(int i=1;i<=100000;i++){
                TextMessage textMessage = session.createTextMessage("msg_"+i);
                producer.send(textMessage);
            }
            //注意：一旦开启事务发送，那么就必须使用commit方法进行事务提交，否则消息无法到达MQ服务器
            session.commit();
            session.close();
            System.out.println("发送10W的ActiveMQ事务消息总耗时："+(System.currentTimeMillis()-oldTime)+"毫秒");
        }catch(JMSException e){
            e.printStackTrace();
            //消息事务回滚
            try{
                session.rollback();
            }catch(JMSException e1){
                e1.printStackTrace();
            }

        }
    }

}