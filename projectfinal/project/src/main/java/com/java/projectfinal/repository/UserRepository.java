package com.java.projectfinal.repository;

import com.java.projectfinal.model.entity.User;
import com.java.projectfinal.model.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    Optional<User> findByRole(UserRole userRole);
}
