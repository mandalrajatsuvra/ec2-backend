package com.maliantala.club.users.maliantalaclubbackend.jms;

import com.maliantala.club.users.maliantalaclubbackend.model.AcknowledgementForSendMessage;
import com.maliantala.club.users.maliantalaclubbackend.model.Message;
import com.maliantala.club.users.maliantalaclubbackend.model.TypingMessagePojo;
import com.maliantala.club.users.maliantalaclubbackend.model.User;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.Topic;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class MessageSenderController {

    @Autowired
    OnlineStatus onlineStatus;

    JmsTemplate jmsTemplate;
    Map<Integer, List<Queue>> queueMap = new HashMap<>();
    @Autowired
    public void setJmsTemplate(JmsTemplate jmsTemplate){
        this.jmsTemplate = jmsTemplate;
        this.jmsTemplate.setDeliveryPersistent(true);
    }
    @CrossOrigin(value = "*")
    @GetMapping(value = "/changeToOfflineStatus/{userId}")
    public ResponseEntity<OnlineStatusPojo> changeOnlineOfflineMap(@PathVariable Integer userId){
        this.onlineStatus.getOnlineStatus().put(userId,0);
        return  ResponseEntity.ok(null);
    }
    @CrossOrigin(value = "*")
    @PostMapping(value = "/sendMessagesToAllUsers")
    public ResponseEntity<Message> sendMessagesToAllUsers(@RequestBody Message message){
        if(queueMap == null && queueMap.isEmpty()){
            return null;
        }else{
            for(Map.Entry<Integer, List<Queue>> e: this.queueMap.entrySet()){
                try {
                    if (!message.getForm().equals(
                            e.getValue().get(3).getQueueName().substring(3,e.getValue().get(3).getQueueName().length()))) {
                        jmsTemplate.convertAndSend(e.getValue().get(2),message);
                        jmsTemplate.convertAndSend(e.getValue().get(3),message);
                    }
                }catch (JMSException ex){ex.printStackTrace();}
            }
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
    }

    @CrossOrigin(value = "*")
    @PostMapping(value = "/sendAcknowledgementToPeer")
    public ResponseEntity<AcknowledgementForSendMessage> sendAcknowledgementToPeer(@RequestBody AcknowledgementForSendMessage acknowledgementForSendMessage)
            throws JMSException {
        System.out.println("acknowledge data is "+ acknowledgementForSendMessage);
        this.jmsTemplate.convertAndSend("ACK".concat(acknowledgementForSendMessage.getTo()),acknowledgementForSendMessage);
        this.jmsTemplate.convertAndSend("seenMsgTopic",acknowledgementForSendMessage);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    @CrossOrigin(value = "*")
    @PostMapping(value = "/sendTypingNotificatioToPeer")
    public ResponseEntity<TypingMessagePojo> sendTypingNotificatioToPeer(@RequestBody TypingMessagePojo typingMessagePojo){
        System.out.println("typing.. message is: "+ typingMessagePojo);
        Topic typingIndicatorTopic = new ActiveMQTopic("typingIndicatorTopic".concat(typingMessagePojo.getTo()));
        this.jmsTemplate.convertAndSend(typingIndicatorTopic,typingMessagePojo);
        return  ResponseEntity.status(HttpStatus.OK).body(null);
    }
    @CrossOrigin(value = "*")
    @PostMapping(value = "/pointToPointMessaging")
    public ResponseEntity<Message> sendMessageToSingleUser(@RequestBody Message message) throws JMSException {
        System.out.println("Message Object"+message);
        Queue readQueue = null;
        Queue readQueueA = null;
        List<Queue> queueList = this.queueMap.get(message.getTo0());
        if(queueList == null || queueList.isEmpty()){
            System.out.println("queueList is empty");
            readQueue = new ActiveMQQueue("ReadQueue".concat(message.getTo0()));
            readQueueA = new ActiveMQQueue("ReadQueueA".concat(message.getTo0()));
        }else{
            System.out.println("queueList is present");
            readQueue = queueList.get(0);
            readQueueA = queueList.get(1);
        }
        if(readQueue !=null) {
            this.jmsTemplate.convertAndSend(readQueue, message);
            System.out.println("queueName is "+readQueue.getQueueName().toString());
            this.jmsTemplate.convertAndSend(readQueueA,message);
            System.out.println("2nd queueName is "+readQueue.getQueueName().toString());
        }
        return  ResponseEntity.ok(null);
    }
    @CrossOrigin(value = "*")
    @PostMapping(value ="/createDedicatedQueue")
    public ResponseEntity<QueueName> createDedicatedQueue(@RequestBody User user){
        String readQueueName = "ReadQueue".concat(user.getId().toString());
        String writeQueueName = "ReadQueueA".concat(user.getId().toString());
        String readGroupQueueStatus = "ReadGroupQueue".concat(user.getId().toString());
        String readGroupQueueMessage = "RGQ".concat(user.getId().toString());
        String acknowledgeMessageQueueName = "ACK".concat(user.getId().toString());
        Queue readQueue = new ActiveMQQueue(readQueueName);
        Queue writeQueue = new ActiveMQQueue(writeQueueName);
        Queue readGroupQueue = new ActiveMQQueue(readGroupQueueStatus);
        Queue readGroupQueueMessageQ = new ActiveMQQueue(readGroupQueueMessage);
        Queue acknowledgeMessageQueue = new ActiveMQQueue(acknowledgeMessageQueueName);
        List<Queue> queueList = new ArrayList<>();
        queueList.add(readQueue);
        queueList.add(writeQueue);
        queueList.add(readGroupQueue);
        queueList.add(readGroupQueueMessageQ);
        queueList.add(acknowledgeMessageQueue);
        this.queueMap.put(user.getId(), queueList);
        QueueName queueName = new QueueName();
        queueName.setReadQueue(readQueueName);
        queueName.setWriteQueue(writeQueueName);
        queueName.setReadGroupName(readGroupQueueStatus);
        return  ResponseEntity.status(HttpStatus.OK).body(queueName);
    }
}
