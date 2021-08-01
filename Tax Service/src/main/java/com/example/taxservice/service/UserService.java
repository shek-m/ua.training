package com.example.taxservice.service;

import com.example.taxservice.entity.User;
import com.example.taxservice.repository.UserRepository;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    @Getter
    private User activeUser;

    @Autowired
    public UserService(UserRepository repository) {
        this.userRepository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        this.activeUser = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User \"" + username + "\" is not registered"));
        return activeUser;
    }

    public void saveNewUser(User user) {
        try {
            userRepository.save(user);
        } catch (Exception ex) {
            log.info("{Почтовый адрес уже существует}");
        }
    }
}