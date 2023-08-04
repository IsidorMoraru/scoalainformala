package com.java.projectfinal.service;

import com.java.projectfinal.model.dto.UserDetailsDto;
import com.java.projectfinal.model.payload.UserRegistrationRequest;
import com.java.projectfinal.model.payload.UserUpdateRequest;

import java.util.Optional;
import java.util.UUID;

public interface UserService {
    boolean createUser(UserRegistrationRequest user);
    boolean existsByEmail(String email);
    Optional<UserDetailsDto> getUserDetails(UUID id);
    boolean deleteUser(UUID id);
    boolean activateUser(UUID id);
    boolean updateUser(UserUpdateRequest user, final UUID id);
}
