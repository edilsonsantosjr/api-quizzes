package com.quizzes.quizzes.dtos;

import jakarta.validation.constraints.NotBlank;

public record RegisterRequestDTO(@NotBlank String name,@NotBlank String email,@NotBlank String password) {
}
