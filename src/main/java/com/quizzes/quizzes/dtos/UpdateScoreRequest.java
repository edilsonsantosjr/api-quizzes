package com.quizzes.quizzes.dtos;

import jakarta.validation.constraints.NotBlank;

public record UpdateScoreRequest(@NotBlank Integer score) {
}
