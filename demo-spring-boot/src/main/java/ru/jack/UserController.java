package ru.jack;


import lombok.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

// Controller - один из типов бинов, которые принимают внешние HTTP-запросы.
// REST
// GET /users/{id}/roles
// GET /users?nameLike='Igor%' // показать юзеров, имя которых начинается с 'Igor'


//@Controller
@RestController // то же самое, что и @Controller, но все методы автоматически помечаются как @ResponseBody
@RequestMapping("/users") // по сути объявляем общую часть пути "/users"

public class UserController {


    // HTTP HyperText Transfer Protocol // запрос-ответ
    // GET POST PUT PATCH DELETE ... HEAD OPTIONS// типы запросов
    // DELETE /users/{id} // удаляет пользователя с указанным id
    // POST /users/{id}  {"id": "1", "name": "newName"} - создание юзера
    // PUT /users/{id}  {"id": "1", "name": "newName"} - изменение указанных полей, остальные становятся null
    // PATCH /users/{id} {"id": "1", "name": "newName"} - изменение только указанных полей


    // http"://ip-address/users -> List<User> //
    // http"://ip-address/users/1 -> User(1, Igor) // образец внешнего запроса с результатом его выполнения
    // http"://ip-address/users?name=Igor -> User(1, Igor) // реквест-параметр

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

    @GetMapping("/{id}")
    public User getUser(@PathVariable long id) {
        return repository.getById(id);
    }

//    @GetMapping
//    public User getUserByName(@RequestParam(required = false) String name) { // "required = false" - параметр необязательный
//        return repository.getByName(name);
//    }

    @GetMapping
    public User getUserByName(@RequestParam String name) {
        return repository.getByName(name);
    }

    @PutMapping("/id}")
    public User updateUser(@PathVariable long id, @RequestBody User user) {
        User existUser = repository.getById(id);
        if (existUser == null) { // 404 если юзер не найден
            throw new IllegalArgumentException();
        }
        existUser.setName(user.getName());
        return existUser;
    }
}
