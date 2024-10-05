package com.quizzes.quizzes.interfaces;

import com.quizzes.quizzes.models.User;

import java.util.List;

public interface UserService {

        User createUser(User user);
        List<User> getAllUsers();
        User getOneUser(String id);
        User updateUser(String id);
        void deleteUser(String id);
}
