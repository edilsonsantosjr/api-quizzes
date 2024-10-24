package com.quizzes.quizzes.dtos;

import com.quizzes.quizzes.models.Question;

import java.time.LocalDateTime;
import java.util.List;

public record QuizRequestDTO(String title, String description, String authorId, int durationSec, Integer score, List<Question> questions) {
}
