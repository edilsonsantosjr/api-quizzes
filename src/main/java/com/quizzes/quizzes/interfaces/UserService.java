package com.quizzes.quizzes.interfaces;

import com.quizzes.quizzes.dtos.UserRequestDTO;
import com.quizzes.quizzes.dtos.UserResponseDTO;

public interface UserService {
        UserResponseDTO getOneUser(String id);
        UserResponseDTO updateUser(String name, UserRequestDTO requestDTO);
        void deleteUser(String id);
}
