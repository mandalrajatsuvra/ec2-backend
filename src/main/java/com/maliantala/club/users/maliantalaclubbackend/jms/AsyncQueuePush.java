package com.maliantala.club.users.maliantalaclubbackend.jms;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import javax.jms.Topic;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class AsyncQueuePush implements Runnable {

    OnlineStatus previousOnlineStatus;

    @Autowired
    OnlineStatus onlineStatus;
    JmsTemplate jmsTemplate;
    private Topic onlineOfflineTopic = new ActiveMQTopic("onLineOfflineTopic");
    @Autowired
    public void setJmsTemplate(JmsTemplate jmsTemplate){
        this.jmsTemplate = jmsTemplate;
        this.jmsTemplate.setDeliveryPersistent(true);
    }
    public void run() {
        boolean singlePass =true;
        this.previousOnlineStatus = new OnlineStatus();
        Semaphore sem = new Semaphore(1);
        ReentrantLock lock = new ReentrantLock();
        for(;;){
            /*if(null != this.onlineStatus && singlePass) {
                this.keepCopyOfPreviousOnlineMap(this.previousOnlineStatus, this.onlineStatus);
                singlePass = false;
            }*/
            lock.lock();
            if(null !=this.onlineStatus) {
                try {
                    Thread.sleep(1000);
                    sem.acquire();
                    if (this.detectChange(this.previousOnlineStatus, this.onlineStatus)) {
                        List<OnlineStatusPojo> onlineStatusPojos = new ArrayList<>();
                       // System.out.println("size " + onlineStatus.getOnlineStatus().size());
                        for (Map.Entry<Integer, Integer> e : onlineStatus.getOnlineStatus().entrySet()) {
                            OnlineStatusPojo onlineStatusPojo = new OnlineStatusPojo();
                            onlineStatusPojo.setUserId(e.getKey());
                            onlineStatusPojo.setOnLine(e.getValue());
                            onlineStatusPojos.add(onlineStatusPojo);
                        }
                        jmsTemplate.convertAndSend(onlineOfflineTopic, onlineStatusPojos);
                        this.keepCopyOfPreviousOnlineMap(this.previousOnlineStatus, this.onlineStatus);
                    }
                    sem.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            lock.unlock();
        }
    }
    AsyncQueuePush()
    {
        new Thread(this).start();
    }
    private void keepCopyOfPreviousOnlineMap(OnlineStatus previous, OnlineStatus now ){
        for(Map.Entry<Integer,Integer> e: now.getOnlineStatus().entrySet()){
            previous.getOnlineStatus().clear();
            previous.getOnlineStatus().put(e.getKey(),e.getValue());
        }
    }
    private boolean detectChange(OnlineStatus previous, OnlineStatus now){
        if(null == now) return false;
        if(previous.getOnlineStatus().size() != now.getOnlineStatus().size()){
            return true;
        }else{
            for(Map.Entry<Integer, Integer> e: now.getOnlineStatus().entrySet()){
               if(e.getValue() != previous.getOnlineStatus().get(e.getKey())){
                   return true;
               }
            }
        }
        return false;
    }
}
