package com.quizzes.quizzes.interfaces;

import com.quizzes.quizzes.dtos.QuizRequestDTO;
import com.quizzes.quizzes.models.Quiz;

public interface QuizService {

    Quiz saveQuiz(QuizRequestDTO quizRequestDTO);

    Quiz getOneQuiz(String id);

}
