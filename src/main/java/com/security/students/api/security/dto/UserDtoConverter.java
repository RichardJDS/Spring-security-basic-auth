package com.security.students.api.security.dto;


import com.security.students.api.security.models.UserEntity;
import com.security.students.api.security.models.UserRole;

import java.util.stream.Collectors;

public class UserDtoConverter {
    public static GetUserDto to(UserEntity user) {
        return GetUserDto.builder()
                .username(user.getUsername())
                .avatar(user.getAvatar())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .roles(user.getRoles().stream()
                        .map(UserRole::name)
                        .collect(Collectors.toSet())
                ).build();
    }
}
