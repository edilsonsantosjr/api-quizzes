package com.quizzes.quizzes.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Question {

    private String question;
    private Option options;
    private String correctAnswer;
}