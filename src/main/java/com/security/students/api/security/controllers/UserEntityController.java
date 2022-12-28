package com.security.students.api.security.controllers;

import com.security.students.api.security.dto.CreateUserDTO;
import com.security.students.api.security.dto.GetUserDto;
import com.security.students.api.security.dto.UserDtoConverter;
import com.security.students.api.security.models.UserEntity;
import com.security.students.api.security.service.CustomDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/users")
public class UserEntityController {
    private final CustomDetailService service;

    @PostMapping
    public GetUserDto createUser(@RequestBody CreateUserDTO userDTO){
        return UserDtoConverter.to(service.nuevoUsuario(userDTO));
    }

    @GetMapping("/me")
    public GetUserDto userAuthenticated(@AuthenticationPrincipal UserEntity user){
        return UserDtoConverter.to(user);
    }
}
