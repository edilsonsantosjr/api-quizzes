package com.quizzes.quizzes.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO para atualização dos dados do usuário")
public record UserRequestDTO(
        @Schema(description = "Nome do usuário", example = "João Silva")
        String name,
        @Schema(description = "Senha do usuário", example = "senha123")
        String password,
        @Schema(description = "URL da foto de perfil do usuário", example = "https://example.com/profile.jpg")
        String profilePicture) {
}
