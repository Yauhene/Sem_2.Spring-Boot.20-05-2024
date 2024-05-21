package ru.jack;

import org.springframework.beans.factory.config.*;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.*;

import java.util.*;

// Грубо - класс-база данных пользователей
@Component  // класс UserRepository объявляется как компонент (Bean)
// @Service - так помечаются бины, где есть бизнес-логика, по функционалу не отличается от @Component
// @Repository - тот же @Component (но есть расширенный функционал), так помечают бины, отвечающие за связь с данными

//== @Component - связка @Component и @Lazy заставит Spring создать экземпляры бинов не при запуске приложения,
//== @Lazy   // а в момент первого обращения к контексту. Такая связка не рекомендуется.

//@Component
//@Scope("singleton") - по умолчанию, создается только один экземпляр бина

//@Component
//@Scope("prototype") - допускается создание более одного экземпляра бина
//@Scope(ConfigurableListableBeanFactory.SCOPE_PROTOTYPE) - то же самое, что и @Scope("prototype")


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
