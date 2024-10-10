package com.quizzes.quizzes.services;

import com.quizzes.quizzes.Repository.UserRepository;
import com.quizzes.quizzes.dtos.UserRequestDTO;
import com.quizzes.quizzes.dtos.UserResponseDTO;
import com.quizzes.quizzes.exceptions.UserException;
import com.quizzes.quizzes.infra.security.SecurityFilter;
import com.quizzes.quizzes.interfaces.UserService;
import com.quizzes.quizzes.models.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public UserResponseDTO getOneUser(String id) {
        Optional<User> userData = userRepository.findById(id);

        if(userData.isEmpty()){
            throw new UserException("User not found");
        }
        User userResponse = userData.get();
        UserResponseDTO userResponseDTO = new UserResponseDTO(userResponse.getId(), userResponse.getName(), userResponse.getEmail(), userResponse.getProfilePicture(),
                userResponse.getCreatedAt(), userResponse.getLocale(), userResponse.getQuizzes());

        return userResponseDTO;
    }

    @Override
    public UserResponseDTO updateUser(String email, UserRequestDTO requestDTO) {

        User userData = userRepository.findByEmail(email).orElseThrow(() -> new UserException("User not found"));

        BeanUtils.copyProperties(requestDTO, userData, "password");

        if(requestDTO.password() != null && !userData.getPassword().isEmpty()){
            String encodePassword = passwordEncoder.encode(requestDTO.password());
            userData.setPassword(encodePassword);
        }

        userRepository.save(userData);

        System.out.println(userData);
        return new UserResponseDTO(userData.getId() ,userData.getName(), userData.getEmail(),
                userData.getProfilePicture());
    }

    @Override
    public void deleteUser(String id) {

    }
}
