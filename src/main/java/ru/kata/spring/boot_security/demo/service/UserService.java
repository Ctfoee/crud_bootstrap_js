package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;


import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final RoleService roleService;


    public UserService(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                user.getAuthorities());
    }

    @Transactional
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Transactional
    public Collection<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public void addUser(User user) {
        userRepository.findByUsername(user.getUsername()).ifPresent((this::flushUser));
        createUser(user);
    }

    @Transactional
    public void updateUser(User newUser) {
        User oldUser = userRepository.findByUsername(newUser.getUsername()).orElseThrow(RuntimeException::new);
        oldUser.setAge(newUser.getAge());
        oldUser.setPassword(passwordEncoder().encode(newUser.getPassword()));
    }

    @Transactional
    public void deleteUser(User user) {
        userRepository.findByUsername(user.getUsername()).ifPresent((this::flushUser));
    }

    @PostConstruct
    public void addRoles() {
        Role user = roleService.getRole("ROLE_USER");
        Role admin = roleService.getRole("ROLE_ADMIN");
        userRepository.save(new User("MainAdmin", passwordEncoder().encode("112233"), 13, List.of(user, admin)));
        userRepository.save(new User("User1", passwordEncoder().encode("123"), 11, List.of(user)));
    }

    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private void createUser(User user) {
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        user.setRoles(List.of(roleService.getRole("ROLE_USER")));
        userRepository.save(user);
    }

    private void flushUser(User user) {
        user.setRoles(null);
        userRepository.delete(user);
    }
}
