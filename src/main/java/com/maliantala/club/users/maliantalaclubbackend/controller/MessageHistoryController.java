package com.maliantala.club.users.maliantalaclubbackend.controller;


import com.maliantala.club.users.maliantalaclubbackend.dao.MessageCurdRepository;
import com.maliantala.club.users.maliantalaclubbackend.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MessageHistoryController {

    @Autowired
    MessageCurdRepository messageCurdRepository;

    @CrossOrigin(value = "*")
    @PostMapping(value = "/saveMessage")
    public ResponseEntity<Message> saveMessage(@RequestBody  Message message){
        Message theMessage =  messageCurdRepository.save(message);
        return  ResponseEntity.status(HttpStatus.OK).body(theMessage);
    }

    @CrossOrigin(value = "*")
    @GetMapping(value = "/getHistoryForPage/{userid}")
    public ResponseEntity<List<Message>> getHistoryForPage(@PathVariable String userid){

        List<Message> list = new ArrayList<>();
        messageCurdRepository.findAll()
                .forEach(message -> {
                        if((message.getForm().equals(userid) || message.getTo0().equals(userid))){
                            list.add(message);
                    }
            });
        List<Message> sortedList = list.stream()
                .sorted(Comparator.comparingLong(Message::getTimestamp))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(sortedList);
    }
}
