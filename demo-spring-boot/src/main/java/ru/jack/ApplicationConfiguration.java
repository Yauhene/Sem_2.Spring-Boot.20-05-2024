package ru.jack;

import org.springframework.context.annotation.*;

// файл конфигурации (java-based конфигурация), обычно используется если бин заимствован из внешней библиотеки
@Configuration
public class ApplicationConfiguration {

    @Bean
    UserRepository myUserRepository() {
        return new UserRepository();
    }

}
