package com.quizzes.quizzes.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document
@Getter
@Setter
@Schema(description = "Representa um quiz com título, descrição, autor, data de criação e outras informações relevantes.")
public class Quiz {

    @Id
    @Schema(description = "Identificador único do quiz", example = "123456")
    private String id;
    @Schema(description = "Título do quiz", example = "Quiz sobre Java")
    private String title;
    @Schema(description = "Descrição breve do quiz", example = "Um quiz para testar conhecimentos em Java básico.")
    private String description;
    @Schema(description = "ID do autor do quiz", example = "987654")
    private String authorId;
    @Schema(description = "Data e hora de criação do quiz", example = "2024-11-01T15:30:00")
    private LocalDateTime createdAt;
    @Schema(description = "Data e hora da última atualização do quiz", example = "2024-11-02T10:00:00")
    private LocalDateTime updateAt;
    @Schema(description = "Duração do quiz em segundos", example = "600")
    private int durationSec;
    @Schema(description = "Pontuação máxima do quiz", example = "100")
    private Integer score;
    @Schema(description = "Lista de questões incluídas no quiz")
    private List<Question> questions;

}
