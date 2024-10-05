package com.quizzes.quizzes.Repository;

import com.quizzes.quizzes.models.Option;
import com.quizzes.quizzes.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
}
