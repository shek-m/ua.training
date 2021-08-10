package com.example.taxservice.service;

import com.example.taxservice.dto.UserDTO;
import com.example.taxservice.entity.User;
import com.example.taxservice.entity.enums.Role;
import com.example.taxservice.repository.UserRepository;
import com.example.taxservice.service.exceptions.UserAlreadyExistException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


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

    public List<User> loadAllRegisteredUsers(){
        return userRepository.findByRole(Role.USER);
    }

    public boolean isUserPresent(Long id) {
        return userRepository.findById(id).isPresent();
    }

    public User registerNewAccountUser(UserDTO userDto) throws UserAlreadyExistException {
        try {
            return userRepository.save(User.builder()
                    .name(userDto.getName())
                    .surname(userDto.getSurname())
                    .email(userDto.getEmail())
                    .role(Role.USER)
                    .username(userDto.getUsername())
                    .date(userDto.getDate())
                    .password(new BCryptPasswordEncoder().encode(userDto.getPassword()))
                    .build());
        } catch (DataIntegrityViolationException ex){
            throw new UserAlreadyExistException(ex.getMessage());
        }
    }
}