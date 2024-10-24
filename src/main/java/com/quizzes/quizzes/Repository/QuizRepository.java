package com.quizzes.quizzes.Repository;

import com.quizzes.quizzes.models.Quiz;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuizRepository extends MongoRepository<Quiz, String> {
}
