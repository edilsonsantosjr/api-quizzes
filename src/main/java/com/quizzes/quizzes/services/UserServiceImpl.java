package com.quizzes.quizzes.services;

import com.quizzes.quizzes.Repository.UserRepository;
import com.quizzes.quizzes.interfaces.UserService;
import com.quizzes.quizzes.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public User getOneUser(String id) {
        return null;
    }

    @Override
    public User updateUser(String id) {
        return null;
    }

    @Override
    public void deleteUser(String id) {

    }
}
