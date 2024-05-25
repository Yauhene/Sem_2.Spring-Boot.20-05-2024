package ru.jack;

import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.context.annotation.*;

// файл конфигурации (java-based конфигурация), обычно используется если бин заимствован из внешней библиотеки
//@Configuration
//@ConditionalOnProperty // включает либо выключает конфигурацию в зависимости от настроек в application.yml
public class ApplicationConfiguration {

    @Bean
    UserRepository myUserRepository() {
        return new UserRepository();
    }

}
