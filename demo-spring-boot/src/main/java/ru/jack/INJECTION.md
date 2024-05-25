
    Чтобы использовать уже имеющийся UserRepository с юзерами, можно видоизменить UserController так:
    ----------------------------------------
1. Инъекции бинов с помощью конструктора:
   Добавить:
   private UserRepository repository;

   public UserController() {
        repository = new UserRepository();
   }
    Изменить @GetMapping:
   @GetMapping(path = "/all") // по сути заменяет @RequestMapping(path = "/users", method = RequestMethod.GET)
   //    @ResponseBody
   public List<User> getUsers() {
   //        return List.of(new User("Igor"), new User("unknown"));
   return repository.getAll();
   }
   ==================================================
    Итоговый листинг UserController:
   @RestController // то же самое, что и @Controller, но все методы автоматически помечаются как  @ResponseBody
   @RequestMapping("/users") // по сути объявляем общую часть пути "/users"

public class UserController {

//    @RequestMapping(path = "/users", method = RequestMethod.GET)

    private UserRepository repository;

    public UserController() {
        repository = new UserRepository(); // создаем объект, не являющийся бином, будет работать

    }

    В данном случае @GetMapping(path = "/all") добавляет "/all" в конец объявленного в @RequestMapping("/users") //
    т.е путь будет "/users.all"
    @GetMapping(path = "/all") // по сути заменяет @RequestMapping(path = "/users", method = RequestMethod.GET)
//    @ResponseBody
    public List<User> getUsers() {
        return repository.getAll();
    }

   ==================================================
    !!! Внедрение бина через конструктор - самый правильный способ
    Для внедрения бина через конструктор переделываем UserController():
   @RestController // то же самое, что и @Controller, но все методы автоматически помечаются как  @ResponseBody
   @RequestMapping("/users") // по сути объявляем общую часть пути "/users"

public class UserController {

    // HTTP HyperText Transfer Protocol // запрос-ответ
    // GET POST PUT PATCH DELETE ... HEAD OPTIONS// типы запросов

    // http"://ip-address/users -> List<User> //
    // http"://ip-address/users/1 -> User(1, Igor) // образец внешнего запроса с результатом его выполнения

//    @RequestMapping(path = "/users", method = RequestMethod.GET)

    private UserRepository repository;

    @Autowired 
    public UserController(UserRepository repository) {
//        repository = new UserRepository();
this.repository = repository; // внедрение бина
}

    // В данном случае @GetMapping(path = "/all") добавляет "/all" в конец объявленного в @RequestMapping("/users") //
    // т.е путь будет "/users.all"
    @GetMapping(path = "/all") // по сути заменяет @RequestMapping(path = "/users", method = RequestMethod.GET)
//    @ResponseBody
    public List<User> getUsers() {
    return repository.getAll();
}
}

    ====================================================
   ----------------------------------------
2. Внедрение бина через сеттеры ===========================================================
    public class UserController {

    private UserRepository repository;

//    @Autowired
//    public UserController(UserRepository repository) {
//        repository = new UserRepository();
//        this.repository = repository; // внедрение бина
//    }
       @Autowired  // так лучше не делать
        public void setRepository(UserRepository repository) {
        this.repository = repository;
        }


    public List<User> getUsers() {
//        return List.of(new User("Igor"), new User("unknown"));
    return repository.getAll();
    }
}
    =========================================================================================
3. Внедрение бина через значение(?) ===========================================================

public class UserController {

//    @Autowired
//    public UserController(UserRepository repository) {
//        repository = new UserRepository();
//        this.repository = repository; // внедрение бина
//    }
//        @Autowired
//        public void setRepository(UserRepository repository) {
//        this.repository = repository;
//        }

    @Autowired
    private UserRepository repository; // так лучше не делать

    public List<User> getUsers() {
//        return List.of(new User("Igor"), new User("unknown"));
return repository.getAll();
    }
}
=========================================================================================
--------------------------------------------------------------------------------
4. Учитывая, что конструктор только один - аннотация @Autowired необязательна
    Используем lombok, установив аннотацию @RequiredArgsConstructor (генерирует конструкторы для всех полей, 
    помеченных как final).
    Соответственно - конструктор нам больше не нужен.
   =========================================================================================
   @RestController
   @RequestMapping("/users") // по сути объявляем общую часть пути "/users"
   @RequiredArgsConstructor // генерирует конструкторы для всех полей, помеченных final

public class UserController {

    private final UserRepository repository;

//    @Autowired
//    public UserController(UserRepository repository) {
//        repository = new UserRepository();
//        this.repository = repository; // внедрение бина
//    }
    @GetMapping(path = "/all") // по сути заменяет @RequestMapping(path = "/users", method = RequestMethod.GET)
    public List<User> getUsers() {
        return repository.getAll();
    }
}
      =========================================================================================

По сути в итоге: =========================================================================================
@RestController
@RequestMapping("/users") // по сути объявляем общую часть пути "/users"
@RequiredArgsConstructor // генерирует конструкторы для всех полей, помеченных final

public class UserController {

    private final UserRepository repository;

    @GetMapping(path = "/all") // по сути заменяет @RequestMapping(path = "/users", method = RequestMethod.GET)
    public List<User> getUsers() {
        return repository.getAll();
    }
}
=========================================================================================
------------------------------------------------------------------------------------------------------------
5. Инъекции через ApplicationConfiguration
    Прежний листинг ===================================================================
   public class ApplicationConfiguration {

   @Bean
   UserRepository myUserRepository() {

        return new UserRepository();
   }

}
        ===================================================================
    Возможен такой вариант: ===============================================
public class ApplicationConfiguration {

    @Bean
    UserRepository myUserRepository() {
        return new UserRepository();
    }

    @Bean
    UserController userController(UserRepository repository) {
        return new UserController(repository);
    }

}
=============================================================================

    