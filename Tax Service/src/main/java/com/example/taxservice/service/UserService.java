package com.example.taxservice.service;

import com.example.taxservice.dto.UserDTO;
import com.example.taxservice.entity.Role;
import com.example.taxservice.entity.User;
import com.example.taxservice.repository.UserRepository;
import com.example.taxservice.validation.DataValidator;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;


    @Autowired
    public UserService(UserRepository repository) {
        this.userRepository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User \"" + username + "\" is not registered"));
    }

    public User registerNewAccountUser(UserDTO userDto) throws UserAlreadyExistException {
        if (emailExists(userDto.getEmail()) || usernameExists(userDto.getUsername())){
            throw new UserAlreadyExistException("There is an account with current email address or username.");
        }

        DataValidator validator = new DataValidator();

        return userRepository.save(User.builder()
                                .name(userDto.getName())
                                .surname(userDto.getSurname())
                                .email(userDto.getEmail())
                                .role(Role.USER)
                                .username(userDto.getUsername())
                                .date(validator.validateDate(userDto.getDate()))
                                .password(new BCryptPasswordEncoder().encode(userDto.getPassword()))
                                .build());
    }

    private boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
    private boolean usernameExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User)authentication.getPrincipal();
    }
}