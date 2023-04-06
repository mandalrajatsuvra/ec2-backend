package com.maliantala.club.users.maliantalaclubbackend.tempstroreAsync;
import com.maliantala.club.users.maliantalaclubbackend.model.Message;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TempStorageController {
    Map<Integer , List<Message>> message = new HashMap<>();
    @CrossOrigin(origins = "*")
    @PostMapping(path = "/saveMessageInTempStorage/{to}")
    public void loadMessages(@RequestBody Message message, @PathVariable Integer to){
        List<Message> previousMessage = this.message.get(to);
        if(previousMessage == null){
            previousMessage = new ArrayList<Message>();
            previousMessage.add(message);
        }else{
            previousMessage.add(message);
        }
        this.message.put(to,previousMessage);
    }
    @CrossOrigin(origins = "*")
    @GetMapping(path = "/getALLUnPulledMessages/{to}")
    public @ResponseBody List<Message> getALLUnPulledMessages(@PathVariable Integer to){
        List<Message>  messageList = this.message.get(to);
        this.message.remove(to);
        return  messageList;
    }
    @GetMapping(path =  "/check")
    public @ResponseBody List<Message> getAllMessages(){
        List<Message> messageList = new ArrayList<>();
        Message message = new Message();
        message.setText("Hi This is Rajat");
        message.setTo0("B");
        message.setForm("A");
        messageList.add(message);
        return  messageList;
    }
}
