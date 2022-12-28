package com.security.students.api.security.service;

import com.security.students.api.security.dto.CreateUserDTO;
import com.security.students.api.security.models.UserEntity;
import com.security.students.api.security.models.UserRole;
import com.security.students.api.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service("userDetailService")
@RequiredArgsConstructor
public class CustomDetailService implements UserDetailsService {
    private final PasswordEncoder passwordEncoder;

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username).orElseThrow(()-> new RuntimeException("username "+ username + "not found"));
    }

    public Optional<UserEntity> findById(long id) {
        return repository.findById(id);
    }

    public List<UserEntity> findAllUserEntity() {
        return repository.findAll();
    }

    public UserEntity saveUserEntity(UserEntity userEntity) {
        return repository.save(userEntity);
    }

    public void deleteUser(long id) {
        repository.deleteById(id);
    }

    public UserEntity nuevoUsuario(CreateUserDTO newUser) {

        if (newUser.getPassword().contentEquals(newUser.getPassword2())) {
            UserEntity userEntity = UserEntity.builder().username(newUser.getUsername())
                    .password(passwordEncoder.encode(newUser.getPassword()))
                    .avatar(newUser.getAvatar())
                    .fullName(newUser.getFullname())
                    .email(newUser.getEmail())
                    .roles(Stream.of(UserRole.USER).collect(Collectors.toSet()))
                    .build();
            try {
                return repository.save(userEntity);
            } catch (DataIntegrityViolationException ex) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre de usuario ya existe");
            }
        } else {
            throw new RuntimeException("New user with different password exception");
        }

    }

}
