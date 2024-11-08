package com.quizzes.quizzes.dtos;

import com.quizzes.quizzes.models.Question;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "DTO para requisição de criação de um quiz")
public record QuizRequestDTO(
        @NotBlank
        @Schema(description = "Título do quiz", example = "Quiz de Matemática")
        String title,
        @NotBlank
        @Schema(description = "Descrição do quiz", example = "Um quiz sobre questões matemáticas básicas")
        String description,
        @NotBlank
        @Schema(description = "ID do autor do quiz", example = "12345")
        String authorId,
        @NotBlank
        @Schema(description = "Duração do quiz em segundos", example = "600")
        int durationSec,
        @NotBlank
        @Schema(description = "Pontuação do quiz", example = "10")
        Integer score,
        @NotBlank
        @Schema(description = "Lista de questões do quiz")
        List<Question> questions) {
}
