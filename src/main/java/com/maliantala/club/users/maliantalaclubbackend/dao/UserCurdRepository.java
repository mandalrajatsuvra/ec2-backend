package com.maliantala.club.users.maliantalaclubbackend.dao;

import com.maliantala.club.users.maliantalaclubbackend.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserCurdRepository extends CrudRepository<User, Integer> {

}
