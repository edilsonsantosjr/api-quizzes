package com.quizzes.quizzes.services;

import com.quizzes.quizzes.Repository.UserRepository;
import com.quizzes.quizzes.dtos.UserRequestDTO;
import com.quizzes.quizzes.dtos.UserResponseDTO;
import com.quizzes.quizzes.exceptions.UserException;
import com.quizzes.quizzes.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getOneUserCase1(){
        String userId = "111";
        User mockUser = new User();
        mockUser.setId(userId);
        mockUser.setName("Edilson Santos");
        mockUser.setEmail("edilson@gmail.com");

        when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));

        UserResponseDTO result = userService.getOneUser(userId);

        assertNotNull(result);
        assertEquals(userId, result.getId());
        assertEquals("Edilson Santos", result.getName());
        assertEquals("edilson@gmail.com", result.getEmail());
    }

    @Test
    void getOneUserCase2(){
        String userId = "invalidUser";

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(UserException.class, () -> userService.getOneUser(userId));
        assertEquals("User not found", exception.getMessage());
    }

    @Test
    void updateUserCase1(){
        String email = "edilson@gmail.com";
        UserRequestDTO requestDTO = new UserRequestDTO("Edilson Santos",  "new_password", "");

        User userData = new User();
        userData.setEmail(email);
        userData.setName("Maria teste");
        userData.setPassword("old_passowrd");

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(userData));
        when(passwordEncoder.encode(requestDTO.password())).thenReturn("encoded_password");
        when(userRepository.save(userData)).thenReturn(userData);

        UserResponseDTO result = userService.updateUser(email, requestDTO);

        assertNotNull(result);
        assertEquals("Edilson Santos", result.getName());
        assertEquals("edilson@gmail.com", result.getEmail());
        assertEquals("encoded_password", userData.getPassword());

        verify(userRepository, times(1)).save(userData);
    }

    @Test
    void updateUserCase2(){
        String email = "notfound@gmail.com";
        UserRequestDTO requestDTO = new UserRequestDTO("Edilson Santos", "new_password", "");
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        Exception exception = assertThrows(UserException.class, () -> userService.updateUser(email, requestDTO));
        assertEquals("User not found", exception.getMessage());

        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void deleteUserCase1(){
        String userId = "user123";
        User user = new User();
        user.setId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        userService.deleteUser(userId);

        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    void deleteUserCase2(){
        String userId = "notfound";

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(UserException.class, () -> userService.deleteUser(userId));
        assertEquals("User not found", exception.getMessage());

        verify(userRepository, never()).deleteById(anyString());
    }

}