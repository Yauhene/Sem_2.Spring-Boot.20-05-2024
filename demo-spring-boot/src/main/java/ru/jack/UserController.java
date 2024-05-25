package ru.jack;


import lombok.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

// Controller - один из типов бинов, которые принимают внешние HTTP-запросы.


//@Controller
@RestController // то же самое, что и @Controller, но все методы автоматически помечаются как  @ResponseBody
@RequestMapping("/users") // по сути объявляем общую часть пути "/users"

public class UserController {

    // HTTP HyperText Transfer Protocol // запрос-ответ
    // GET POST PUT PATCH DELETE ... HEAD OPTIONS// типы запросов

    // http"://ip-address/users -> List<User> //
    // http"://ip-address/users/1 -> User(1, Igor) // образец внешнего запроса с результатом его выполнения

    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository; // внедрение бина
    }

    // В данном случае @GetMapping(path = "/all") добавляет "/all" в конец объявленного в @RequestMapping("/users") //
    // т.е путь будет "/users.all"
    //    @RequestMapping(path = "/users", method = RequestMethod.GET)
    @GetMapping(path = "/all") // по сути заменяет @RequestMapping(path = "/users", method = RequestMethod.GET)
//    @ResponseBody
    public List<User> getUsers() {
        return repository.getAll();
    }
}
