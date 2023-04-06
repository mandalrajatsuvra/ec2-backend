package com.maliantala.club.users.maliantalaclubbackend.websocketAsync;


import com.maliantala.club.users.maliantalaclubbackend.model.MessageHistory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class MessageHistoryStorageController {

        Map<String,MessageHistory> messageHistory = new HashMap<>();
        @CrossOrigin(value = "*")
        @PostMapping(path = "storeMessageHistory/{to}/{form}")
        public void storeMessageHistory(@PathVariable String to, @PathVariable String form , @RequestBody MessageHistory messageHistory){
                String toAndFrom = to.concat("-").concat(form);
                this.messageHistory.put(toAndFrom,messageHistory);
        }
        @CrossOrigin(value = "*")
        @GetMapping(path = "loadMessageHistory/{to}/{form}")
        public ResponseEntity<MessageHistory> loadMessageHistory(@PathVariable String to , @PathVariable String form){
            String toAndFrom = to.concat("-").concat(form);
            return ResponseEntity.status(HttpStatus.OK).body(messageHistory.get(toAndFrom));
        }
}
