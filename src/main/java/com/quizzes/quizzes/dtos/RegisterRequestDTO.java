package com.quizzes.quizzes.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "DTO para solicitação de registro do usuário")
public record RegisterRequestDTO(
        @NotBlank
        @Schema(description = "Nome completo do usuário", example = "João da Silva")
        String name,
        @NotBlank
        @Schema(description = "Email do usuário", example = "usuario@exemplo.com")
        String email,
        @NotBlank
        @Schema(description = "Senha do usuário", example = "senha123")
        String password) {
}
