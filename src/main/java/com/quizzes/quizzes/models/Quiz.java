package com.quizzes.quizzes.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document
@Getter
@Setter
public class Quiz {

    @Id
    private String id;
    private String title;
    private String description;
    private String authorId;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
    private int durationSec;
    private Integer score;
    private List<Question> questions;

}
