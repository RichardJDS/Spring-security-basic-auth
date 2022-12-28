package com.security.students.api.security.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserDTO {
    private String username;
    private String avatar;
    private String fullname;
    private String email;
    private String password;
    private String password2;
}
