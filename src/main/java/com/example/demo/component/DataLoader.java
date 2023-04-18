package com.example.demo.component;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.entity.enums.PermissionEnum;
import com.example.demo.entity.enums.RoleEnum;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Value("${app.admin.password}")
    private String adminPassword;

    @Value("${app.admin.firstName}")
    private String adminFirstName;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String modeType;

    @Override
    @Transactional
    public void run(String... args) {
        if (Objects.equals("create", modeType))
            addAdmin();

    }

    private void addAdmin() {
        userRepository.save(
                User.builder()
                        .name(adminFirstName)
                        .password(passwordEncoder.encode(adminPassword))
                        .role(addSuperAdminRole())
                        .enable(true)
                        .build()
        );
    }

    private Role addSuperAdminRole() {
        return roleRepository.save(
                Role.builder()
                        .name(RoleEnum.ADMIN)
                        .description("System owner")
                        .permissions(getAdminPermissions())
                        .build()
        );
    }

    private Set<PermissionEnum> getAdminPermissions() {
        return Set.of(PermissionEnum.values());
    }


}
