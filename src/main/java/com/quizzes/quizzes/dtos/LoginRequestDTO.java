package com.quizzes.quizzes.dtos;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(@NotBlank String email,@NotBlank String password) {}
