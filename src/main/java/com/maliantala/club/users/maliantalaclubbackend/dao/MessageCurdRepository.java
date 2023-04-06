package com.maliantala.club.users.maliantalaclubbackend.dao;

import com.maliantala.club.users.maliantalaclubbackend.model.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageCurdRepository  extends CrudRepository<Message, Integer> {

}
