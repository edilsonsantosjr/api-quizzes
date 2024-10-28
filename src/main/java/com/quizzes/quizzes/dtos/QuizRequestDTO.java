package com.quizzes.quizzes.dtos;

import com.quizzes.quizzes.models.Question;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public record QuizRequestDTO(@NotBlank String title,@NotBlank String description,@NotBlank String authorId,
                            @NotBlank int durationSec,@NotBlank Integer score,@NotBlank List<Question> questions) {
}
