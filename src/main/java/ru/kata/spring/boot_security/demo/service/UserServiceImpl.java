package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositoriy.RoleRepository;
import ru.kata.spring.boot_security.demo.repositoriy.UserRepository;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User getUser(Long id) {
        return userRepository.getById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public void addUser(User newUser) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userRepository.saveAndFlush(newUser);
    }

    @Transactional
    public void updateUser(User updatedUser) {
        if(!updatedUser.getPassword().equals(userRepository.getById(updatedUser.getId()).getPassword())) {
            updatedUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }
        userRepository.saveAndFlush(updatedUser);
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email);
    }

    public Set<Role> getRoles() {
        return Set.copyOf(roleRepository.findAll());
    }
}
