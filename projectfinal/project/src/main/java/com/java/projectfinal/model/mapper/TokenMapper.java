package com.java.projectfinal.model.mapper;

import com.java.projectfinal.model.dto.TokenDto;
import com.java.projectfinal.model.entity.User;
import com.java.projectfinal.security.token.Token;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TokenMapper {

    @Mapping(target = "userId", source = "user", qualifiedByName = "mapUserId")
    TokenDto toTokenDto(Token token);

    @Named("mapUserId")
    default UUID mapUserId(User user) {
        return user != null ? user.getId() : null;
    }

    Token toToken(TokenDto tokenDto);
}