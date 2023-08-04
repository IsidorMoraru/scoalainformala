package com.java.projectfinal.model.dto;

import java.util.UUID;

public record UserDto(UUID id, String email, String firstName, String lastName) {
}