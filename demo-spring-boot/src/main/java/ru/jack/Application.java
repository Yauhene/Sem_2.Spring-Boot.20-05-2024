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

// UserRepository, возвращаемый через строку-имя будет иметь тип Object и требовать кастования к UserRepository
//		UserRepository userRepository = (UserRepository) context.getBean("myUserRepository");
// Без кастования:
//		UserRepository userRepository11 = context.getBean("myUserRepository", UserRepository.class);
//		UserRepository userRepository = new UserRepository();
// Новая версия с участием Spring:
//		UserRepository userRepository1 = context.getBean(UserRepository.class);
//		UserRepository userRepository2 = context.getBean(UserRepository.class);
//		UserRepository userRepository3 = context.getBean(UserRepository.class);

//		System.out.println(userRepository1.getAll());
//		System.out.println(userRepository1.getById(2));
//		System.out.println(userRepository == userRepository1);
//		System.out.println(userRepository1 == userRepository2);
//		System.out.println(userRepository1 == userRepository3);

	}

}
