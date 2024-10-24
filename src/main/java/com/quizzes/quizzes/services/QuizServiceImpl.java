package com.quizzes.quizzes.services;

import com.quizzes.quizzes.Repository.QuizRepository;
import com.quizzes.quizzes.dtos.QuizRequestDTO;
import com.quizzes.quizzes.exceptions.QuizException;
import com.quizzes.quizzes.interfaces.QuizService;
import com.quizzes.quizzes.models.Quiz;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizRepository quizRepository;
    @Override
    public Quiz saveQuiz(QuizRequestDTO quizRequestDTO) {
        Quiz quizModel = new Quiz();
        BeanUtils.copyProperties(quizRequestDTO, quizModel);

        LocalDateTime createdAt = LocalDateTime.now();
        quizModel.setCreatedAt(createdAt);

        quizRepository.save(quizModel);
        return quizModel;
    }

    @Override
    public Quiz getOneQuiz(String id) {
        Optional<Quiz> quiz = quizRepository.findById(id);

        if(quiz.isEmpty()){
            throw new QuizException("Quiz not found");
        }

        return quiz.get();
    }
}
