package ru.jack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.*;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

//		SpringApplication.run(Application.class, args);
// Новая версия с участием Spring:
		ApplicationContext context = SpringApplication.run(Application.class, args);
//		UserRepository userRepository = new UserRepository();
// Новая версия с участием Spring:
		UserRepository userRepository = context.getBean(UserRepository.class);
				System.out.println(userRepository.getAll());
		System.out.println(userRepository.getById(2));
	}

}
