package com.maliantala.club.users.maliantalaclubbackend.restservices;


import com.maliantala.club.users.maliantalaclubbackend.dao.UserCurdRepository;
import com.maliantala.club.users.maliantalaclubbackend.jms.OnlineStatus;
import com.maliantala.club.users.maliantalaclubbackend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
public class UserRestController {

    @Autowired
    private UserCurdRepository userCurdRepository;

    @Autowired
    private OnlineStatus onlineStatus;

    @CrossOrigin(value = "*")
    @PostMapping(path = "/adduser")
    public ResponseEntity<User>  addUser(@RequestBody User user){
        System.out.println(user.getImageurl());

        User theReturnedUser= userCurdRepository.save(user);
        return ResponseEntity.status(HttpStatus.OK).body(theReturnedUser);
    }
    @CrossOrigin(value = "*")
    @GetMapping(path = "/allusers/{userId}")
    public  @ResponseBody ResponseEntity<List<User>>getAllUsers(@PathVariable Integer userId){
        Iterable<User> users=  userCurdRepository.findAll();
        Iterator<User> iterator = users.iterator();
        List<User> userList = new ArrayList<>();
        while(iterator.hasNext()){
            User user = iterator.next();
            if(user.getId() != userId) {
                userList.add(user);
            }
        }
        return  ResponseEntity.status(HttpStatus.OK).body(userList);
    }
    @CrossOrigin(value = "*")
    @PostMapping(path = "/getSpecificUser")
    public ResponseEntity<User> getSpecificUser(@RequestBody User user) {
        if(null != user.getUsername() && !"".equals(user.getUsername())
                && null!= user.getPassword()  && !"".equals(user.getPassword())){
            Iterable<User> iterable = userCurdRepository.findAll();
            for(Iterator<User> iterator = iterable.iterator() ; iterator.hasNext() ; ){
                User tempUser = iterator.next();
                if(tempUser.getUsername().equals(user.getUsername())
                        && tempUser.getPassword().equals(user.getPassword())){
                    System.out.println("user id:  "+user.getId());
                      this.onlineStatus.getOnlineStatus().put(tempUser.getId(),1);
                      return  ResponseEntity.status(HttpStatus.OK).body(tempUser);
                }
            }
            return null;
        }
        return  null;
    }
}
