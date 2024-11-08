package com.quizzes.quizzes.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "DTO para atualização da pontuação de um quiz")
public record UpdateScoreRequest(

        @NotBlank
        @Schema(description = "Pontuação atualizada para o quiz", example = "15")
        Integer score) {
}
