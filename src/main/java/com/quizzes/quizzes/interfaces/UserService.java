package com.quizzes.quizzes.interfaces;

import com.quizzes.quizzes.dtos.UserRequestDTO;
import com.quizzes.quizzes.dtos.UserResponseDTO;
import com.quizzes.quizzes.models.User;

import java.util.List;

public interface UserService {
        List<User> getAllUsers();
        UserResponseDTO getOneUser(String id);
        UserResponseDTO updateUser(String name, UserRequestDTO requestDTO);
        void deleteUser(String id);
}
