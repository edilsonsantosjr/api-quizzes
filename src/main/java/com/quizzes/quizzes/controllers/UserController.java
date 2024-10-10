package com.quizzes.quizzes.controllers;

import com.quizzes.quizzes.dtos.UserRequestDTO;
import com.quizzes.quizzes.dtos.UserResponseDTO;
import com.quizzes.quizzes.exceptions.UserException;
import com.quizzes.quizzes.models.User;
import com.quizzes.quizzes.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping
    public ResponseEntity<Object> getUser(Authentication authentication) {
        User authenticatedUser = (User) authentication.getPrincipal();
        UserResponseDTO userModelObj;
        try {
            userModelObj = userService.getOneUser(authenticatedUser.getId());
        } catch (UserException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(userModelObj);

    }

    @PutMapping
    public ResponseEntity<Object> updateUser(Authentication authentication, @RequestBody UserRequestDTO userRequestDTO){
        User authenticatedUser = (User) authentication.getPrincipal();
        String email = authenticatedUser.getEmail();
        System.out.println(email);
        UserResponseDTO userResponseDTO;
        try {
            userResponseDTO = userService.updateUser(email, userRequestDTO);
        } catch (UserException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(userResponseDTO);
    }


}
