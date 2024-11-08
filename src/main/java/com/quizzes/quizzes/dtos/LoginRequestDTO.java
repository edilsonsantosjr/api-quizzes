package com.quizzes.quizzes.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "DTO para solicitação de login do usuário")
public record LoginRequestDTO(
        @NotBlank
        @Schema(description = "Email do usuário", example = "usuario@exemplo.com")
        String email,
        @NotBlank
        @Schema(description = "Senha do usuário", example = "senha123")
        String password) {}
