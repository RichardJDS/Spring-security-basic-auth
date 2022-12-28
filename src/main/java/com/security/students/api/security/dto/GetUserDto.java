package com.security.students.api.security.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetUserDto {
    private String username;
    private String avatar;
    private String fullName;
    private String email;
    private Set<String> roles;
}
