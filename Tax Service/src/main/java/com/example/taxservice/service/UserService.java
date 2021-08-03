package com.example.taxservice.service;

import com.example.taxservice.entity.Role;
import com.example.taxservice.entity.User;
import com.example.taxservice.repository.UserRepository;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;


@Slf4j
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;


    @Autowired
    public UserService(UserRepository repository) {
        this.userRepository = repository;
    }

//    @PostConstruct
//    public void init(){
//        if (!userRepository.findByUsername("Michael").isPresent()){
//            saveNewUser(User.builder()
//                                    .username("Michael")
//                                    .password(new BCryptPasswordEncoder().encode("iamadmin"))
//                                    .role(Role.ADMIN)
//                                    .build());
//        }
//    }

    @Override
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User \"" + username + "\" is not registered"));
    }

    public void saveNewUser(User user) {
        try {
            userRepository.save(user);
        } catch (Exception ex) {
            log.info("{Почтовый адрес уже существует}");
        }
    }
}