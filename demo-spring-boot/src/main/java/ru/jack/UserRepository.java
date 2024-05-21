package ru.jack;

import java.util.*;

// Грубо - класс-база данных пользователей
public class UserRepository {

    private final List<User> users;

    public UserRepository() {
        this.users = new ArrayList<>();
        users.add(new User("Igor"));
        users.add(new User("User #2"));
        users.add(new User("User #3"));
        users.add(new User("User #4"));
        users.add(new User("User #5"));
    }

    public List<User> getAll() {
//        используется List.copyOf(users), в целях не дать кому-то получить оригинальный список users,
//        только копию
        return List.copyOf(users);
    }

    public User getById(long id) {
        return users.stream()
                .filter(it -> Objects.equals(it.getId(), id)) // фильтрует пользователей по значению id
                .findFirst() // выбирает первый объект из отфильтрованных
                .orElse(null); // возвращает null при неудачном поиске
    }
}
