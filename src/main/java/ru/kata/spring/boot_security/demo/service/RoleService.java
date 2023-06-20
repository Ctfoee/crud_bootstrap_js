package ru.kata.spring.boot_security.demo.service;


import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;

import javax.annotation.PostConstruct;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getRole(String role) {
        return roleRepository.findByRole(role)
                .orElseThrow(IllegalArgumentException::new);
    }

    @PostConstruct
    public void addRoles() {
        roleRepository.save(new Role(1L, "ROLE_USER"));
        roleRepository.save(new Role(2L, "ROLE_ADMIN"));
    }
}
