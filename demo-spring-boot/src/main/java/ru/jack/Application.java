package ru.jack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);

		UserRepository userRepository = new UserRepository();
		System.out.println(userRepository.getAll());
		System.out.println(userRepository.getById(2));
	}

}
