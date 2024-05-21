package ru.jack;

import lombok.*;

@Data
public class User {
    private static long idCounter = 1L;
    private final long id;
    private final String name;

    public User(String name) {
        this.id = idCounter++;
        this.name = name;
    }
}
