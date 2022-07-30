package ru.kata.spring.boot_security.demo.dataloader;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer implements ApplicationRunner {

    private final UserService userService;
    private final RoleDao roleDao;

    public DataInitializer(UserService userService, RoleDao roleDao) {
        this.userService = userService;
        this.roleDao = roleDao;
    }

    @Transactional
    public void run(ApplicationArguments args) {
        User user = new User();
        Set<Role> roleSet = new HashSet<>();

        roleDao.add(new Role("USER"));
        roleDao.add(new Role("ADMIN"));


        user.setEmail("q");
        user.setPassword(("q"));
        user.setName("Ivan");
        user.setSurname("Ivanov");
        user.setAge(11);
        user.setRoles(Set.copyOf(roleDao.getAllRoles()));
        userService.addUser(user);


//        user.setEmail("user1@mail.ru");
//        user.setPassword(passwordEncoder.encode("user1"));
//        user.setName("Pavel");
//        user.setSurname("Pavlov");
//        user.setAge(22);
//        user.setRoles(roleSet);
//        userService.addUser(user);
//
//        roleSet.clear();
//        roleSet.add(new Role(2L, "ADMIN"));
//
//
//        user.setEmail("admin@mail.ru");
//        user.setPassword(passwordEncoder.encode("admin"));
//        user.setName("Nikolay");
//        user.setSurname("Nikolaev");
//        user.setAge(33);
//        user.setRoles(roleSet);
//        userService.addUser(user);
//
//        roleSet.add(new Role(1L, "USER"));
//
//
//        user.setEmail("admin1@mail.ru");
//        user.setPassword(passwordEncoder.encode("admin1"));
//        user.setName("Andrey");
//        user.setSurname("Andreev");
//        user.setAge(44);
//        user.setRoles(roleSet);
//        userService.addUser(user);
    }
}
