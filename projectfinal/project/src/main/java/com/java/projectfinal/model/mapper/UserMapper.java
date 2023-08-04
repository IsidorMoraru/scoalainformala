package com.java.projectfinal.model.mapper;

import com.java.projectfinal.model.dto.UserDto;
import com.java.projectfinal.model.entity.User;
import com.java.projectfinal.model.payload.UserRegistrationRequest;
import com.java.projectfinal.model.payload.UserUpdateRequest;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import javax.annotation.processing.Generated;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2023-06-28T15:09:41+0300",
        comments = "version: 1.5.3.Final, compiler: javac, environment: Java 18.0.2.1 (Oracle Corporation)"
)
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto map(User user);

    @InheritInverseConfiguration(name = "map")
    User map(UserDto userDto);

    User registrationRequestToUser(UserRegistrationRequest userRegistrationRequest);

    User updateRequestToUser(UserUpdateRequest userUpdateRequest, @MappingTarget User initial);

}
