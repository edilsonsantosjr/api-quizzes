package com.quizzes.quizzes.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document
public class Quiz {

    @Id
    private String id;
    private String title;
    private String description;
    private String authorId;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
    private boolean isActive;
    private int duration;
    private List<Question> questions;
}
