package com.quizzes.quizzes.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Question {

    @Id
    private String questionId;
    private String content;
    private QuestionType questionType;
    private List<Option> options;
}
