package com.example.taxservice.service;

import com.example.taxservice.dto.UserDTO;
import com.example.taxservice.entity.User;
import com.example.taxservice.repository.UserRepository;
import com.example.taxservice.service.exceptions.UserAlreadyExistException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    public void testLoadUserByUsername(){
        User user = mock(User.class);
        Optional<User> userOptional = Optional.of(user);
        when(userRepository.findByUsername(anyString())).thenReturn(userOptional);
        User result = (User) userService.loadUserByUsername("user");
        Assert.assertEquals("result", result, user);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void testLoadUserByUsername_UserNotFound(){
        User user = mock(User.class);
        Optional<User> userOptional = Optional.of(user);
        when(userRepository.findByUsername(anyString())).thenThrow(UsernameNotFoundException.class);
        userService.loadUserByUsername("user");
    }

    @Test
    public void testRegisterNewAccountUser() throws UserAlreadyExistException {
        UserDTO dto = mock(UserDTO.class);
        User user = mock(User.class);
        when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
        User result = userService.registerNewAccountUser(dto);
        Assert.assertEquals("result", result, user);
    }

    @Test(expected = UserAlreadyExistException.class)
    public void testRegisterNewAccountUser_UserAlreadyExists() throws UserAlreadyExistException {
        UserDTO dto = mock(UserDTO.class);
        User user = mock(User.class);
        when(userRepository.save(Mockito.any(User.class))).thenThrow(DataIntegrityViolationException.class);
        User result = userService.registerNewAccountUser(dto);
    }
}