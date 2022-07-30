package ru.kata.spring.boot_security.demo.dataloader;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositoriy.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitalizer implements ApplicationRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitalizer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void run(ApplicationArguments args) {
        User user = new User();
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(new Role(1L, "USER"));

        user.setId(1L);
        user.setEmail("user@mail.ru");
        user.setPassword(passwordEncoder.encode("user"));
        user.setName("Ivan");
        user.setSurname("Ivanov");
        user.setAge(11);
        user.setRoles(roleSet);
        userRepository.save(user);

        user.setId(2L);
        user.setEmail("user1@mail.ru");
        user.setPassword(passwordEncoder.encode("user1"));
        user.setName("Pavel");
        user.setSurname("Pavlov");
        user.setAge(22);
        user.setRoles(roleSet);
        userRepository.save(user);

        roleSet.clear();
        roleSet.add(new Role(2L, "ADMIN"));

        user.setId(3L);
        user.setEmail("admin@mail.ru");
        user.setPassword(passwordEncoder.encode("admin"));
        user.setName("Nikolay");
        user.setSurname("Nikolaev");
        user.setAge(33);
        user.setRoles(roleSet);
        userRepository.save(user);

        roleSet.add(new Role(1L, "USER"));

        user.setId(4L);
        user.setEmail("admin1@mail.ru");
        user.setPassword(passwordEncoder.encode("admin1"));
        user.setName("Andrey");
        user.setSurname("Andreev");
        user.setAge(44);
        user.setRoles(roleSet);
        userRepository.save(user);
    }
}
