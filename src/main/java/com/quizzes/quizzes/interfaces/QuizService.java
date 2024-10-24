package com.quizzes.quizzes.interfaces;

import com.quizzes.quizzes.dtos.QuizRequestDTO;
import com.quizzes.quizzes.models.Quiz;

import java.util.List;

public interface QuizService {

    Quiz saveQuiz(QuizRequestDTO quizRequestDTO);

    Quiz getOneQuiz(String id);

    List<Quiz> getAllQuiz(String id);

    Quiz updateQuiz(String id, Integer score);

    void deleteQuiz(String id);
}
