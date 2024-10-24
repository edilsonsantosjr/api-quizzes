package com.quizzes.quizzes.dtos;

import com.quizzes.quizzes.models.Quiz;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
public class UserResponseDTO {

    private String id;
    private String name;
    private String email;
    private String profilePicture;
    private LocalDateTime createAt;
    private String locale;
    private List<Quiz> quizzes;

    public UserResponseDTO(String id, String name, String email, String profilePicture, LocalDateTime createAt, String locale) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.profilePicture = profilePicture;
        this.createAt = createAt;
        this.locale = locale;
    }

    public UserResponseDTO(String id,String name, String email, String profilePicture) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.profilePicture = profilePicture;
    }
}
