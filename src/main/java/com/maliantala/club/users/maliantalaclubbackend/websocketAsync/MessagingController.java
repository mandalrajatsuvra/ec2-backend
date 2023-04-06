package com.maliantala.club.users.maliantalaclubbackend.websocketAsync;

import com.maliantala.club.users.maliantalaclubbackend.model.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MessagingController {

    @MessageMapping("/sendMessages")
    @SendTo(value = "/topic/maliantala-topic")
    public Message sendMessagesApi(@RequestBody Message message){
        return message;
    }

}
