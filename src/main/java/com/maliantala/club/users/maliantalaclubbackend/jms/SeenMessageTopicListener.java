package com.maliantala.club.users.maliantalaclubbackend.jms;


import com.maliantala.club.users.maliantalaclubbackend.dao.MessageCurdRepository;
import com.maliantala.club.users.maliantalaclubbackend.model.AcknowledgementForSendMessage;
import com.maliantala.club.users.maliantalaclubbackend.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SeenMessageTopicListener {
    @Autowired
    MessageCurdRepository messageCurdRepository;

    @JmsListener(destination = "seenMsgTopic" ,containerFactory = "myFactory")
    public void seenMsgTopicLintener(AcknowledgementForSendMessage message){
        if(null != message.getMessageId()){
            Optional<Message> optionalMessage= messageCurdRepository.findById(message.getMessageId());
            if(optionalMessage.isPresent()){
                Message temp=   optionalMessage.get();
                temp.setHasSeenByPeer("YES");
                messageCurdRepository.save(temp);
            }
        }
    }
}
