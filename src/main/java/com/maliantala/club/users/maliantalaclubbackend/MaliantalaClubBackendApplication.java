package com.maliantala.club.users.maliantalaclubbackend;

import com.maliantala.club.users.maliantalaclubbackend.jms.AsyncQueuePush;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;


@SpringBootApplication
@EnableJms
public class MaliantalaClubBackendApplication {
	@Autowired
	AsyncQueuePush asyncQueuePush;

	public static void main(String[] args)
	{
		SpringApplication.run(MaliantalaClubBackendApplication.class, args);
	}

}
