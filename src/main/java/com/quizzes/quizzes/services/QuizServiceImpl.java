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
import java.util.List;
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

    @Override
    public List<Quiz> getAllQuiz(String id) {
        List<Quiz> quizList = quizRepository.findByAuthorId(id);
        if(quizList.isEmpty()){
            throw new QuizException("Quiz not found");
        }

        return quizList;
    }

    @Override
    public Quiz updateQuiz(String id, Integer score) {
        Optional<Quiz> quizModel = quizRepository.findById(id);
        if(quizModel.isEmpty()){
            throw new QuizException("Quiz not found");
        }
        quizModel.get().setScore(score);
        LocalDateTime updateAt = LocalDateTime.now();
        quizModel.get().setUpdateAt(updateAt);

        return quizRepository.save(quizModel.get());
    }

    @Override
    public void deleteQuiz(String id) {
        Optional<Quiz> quizModel = quizRepository.findById(id);

        if(quizModel.isEmpty()){
            throw new QuizException("Quiz not found");
        }

        quizRepository.delete(quizModel.get());
    }
}
