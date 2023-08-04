package com.java.projectfinal.service;

import com.java.projectfinal.model.dto.UserDetailsDto;
import com.java.projectfinal.model.entity.User;
import com.java.projectfinal.model.entity.UserRole;
import com.java.projectfinal.model.entity.UserStatus;
import com.java.projectfinal.model.mapper.UserMapper;
import com.java.projectfinal.model.payload.UserRegistrationRequest;
import com.java.projectfinal.model.payload.UserUpdateRequest;
import com.java.projectfinal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean createUser(UserRegistrationRequest user) {
        User newUser = new User();
        newUser.setStatus(UserStatus.INACTIVE);
        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setDateOfBirth(user.getDateOfBirth());
        newUser.setPhone(user.getPhone());
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        newUser.setPassword(encodedPassword);
        newUser.setSalutation(user.getSalutation());
        newUser.setAddress(user.getAddress());
        newUser.setRole(UserRole.USER);
        User us = userRepository.save(newUser);
        return us != null;
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public Optional<UserDetailsDto> getUserDetails(UUID id) {
        Optional<User> userDetails = userRepository.findById(id);
        if (userDetails.isEmpty()) {
            return Optional.empty();
        }
        User user = userDetails.get();
        UserDetailsDto userDetailsDto = new UserDetailsDto();
        userDetailsDto.setEmail(user.getEmail());
        userDetailsDto.setFirstName(user.getFirstName());
        userDetailsDto.setLastName(user.getLastName());
        userDetailsDto.setAddress(user.getAddress());
        userDetailsDto.setDateOfBirth(user.getDateOfBirth());
        userDetailsDto.setPassword(user.getPassword());
        userDetailsDto.setPhone(user.getPhone());
        userDetailsDto.setSalutation(user.getSalutation());
        userDetailsDto.setRole(UserRole.valueOf(user.getRole().name()));
        return Optional.of(userDetailsDto);
    }

    @Override
    public boolean deleteUser(final UUID id) {
        userRepository.deleteById(id);
        return !userRepository.existsById(id);
    }

    @Override
    public boolean activateUser(UUID id) {
        return userRepository.findById(id).map(user -> {
            user.setStatus(UserStatus.ACTIVE);
            return UserStatus.ACTIVE.equals(userRepository.save(user).getStatus());
        }).orElse(false);
    }

    @Override
    public boolean updateUser(final UserUpdateRequest user, final UUID id) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    User mappedUser = userMapper.updateRequestToUser(user, existingUser);
                    mappedUser.setId(existingUser.getId());
                    mappedUser.setPassword(existingUser.getPassword());
                    mappedUser.setStatus(existingUser.getStatus());
                    mappedUser.setRole(existingUser.getRole());
                    userRepository.save(mappedUser);
                    return true;
                }).orElse(false);
    }

}

