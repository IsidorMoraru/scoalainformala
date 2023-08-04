package com.java.projectfinal.model.dto;

import com.java.projectfinal.security.token.TokenType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
public class TokenDto {

    private int id;
    private String token;
    private TokenType tokenType;
    private boolean revoked;
    private boolean expired;
    private UUID userId;
}
