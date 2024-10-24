package com.quizzes.quizzes.Repository;

import com.quizzes.quizzes.models.Quiz;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface QuizRepository extends MongoRepository<Quiz, String> {
    List<Quiz> findByAuthorId(String id);
}
