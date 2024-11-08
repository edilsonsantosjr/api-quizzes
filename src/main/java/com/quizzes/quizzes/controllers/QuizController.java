package com.quizzes.quizzes.controllers;

import com.quizzes.quizzes.dtos.QuizRequestDTO;
import com.quizzes.quizzes.dtos.UpdateScoreRequest;
import com.quizzes.quizzes.exceptions.QuizException;
import com.quizzes.quizzes.models.Quiz;
import com.quizzes.quizzes.models.User;
import com.quizzes.quizzes.services.OpenAIService;
import com.quizzes.quizzes.services.QuizServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/quizzes")
public class QuizController {

    @Autowired
    private OpenAIService openAIService;
    @Autowired
    private QuizServiceImpl quizService;

    @Operation(summary = "Gerar um quiz", description = "Gera um quiz com base em um tema especificado pelo usuário.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Quiz gerado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "Erro ao gerar o quiz",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class)))
    })
    @GetMapping("/generate")
    public Mono<ResponseEntity<String>> generateQuiz(@RequestParam String topic){
        return openAIService.generateQuiz(topic)
                .map(ResponseEntity::ok)
                .onErrorResume(e -> Mono.just(ResponseEntity.badRequest().body("Error generating quiz: " + e.getMessage())));
    }

    @Operation(summary = "Criar um novo quiz", description = "Cria um quiz a partir dos dados fornecidos pelo usuário no corpo da requisição.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Quiz criado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Quiz.class))),
            @ApiResponse(responseCode = "404", description = "Erro ao salvar o quiz",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class)))
    })
    @PostMapping
    public ResponseEntity<Object> saveQuiz(@RequestBody QuizRequestDTO quizRequestDTO){
        Quiz quizModel;
        try {
            quizModel = quizService.saveQuiz(quizRequestDTO);
        } catch (QuizException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(quizModel);
    }

    @Operation(summary = "Obter um quiz pelo ID", description = "Recupera um quiz específico a partir do seu ID. Retorna os dados completos do quiz.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Quiz encontrado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Quiz.class))),
            @ApiResponse(responseCode = "404", description = "Quiz não encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneQuiz(@PathVariable(value = "id") String id){
        Quiz quizModel;
        try {
            quizModel = quizService.getOneQuiz(id);

        } catch (QuizException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(quizModel);
    }

    @Operation(summary = "Obter todos os quizzes de um autor", description = "Recupera todos os quizzes associados a um autor autenticado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de quizzes encontrada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Quiz.class, type = "array"))),
            @ApiResponse(responseCode = "404", description = "Nenhum quiz encontrado para o autor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class)))
    })
    @GetMapping
    public ResponseEntity<Object> getAllQuiz(Authentication authentication){
        User authenticationPrincipal = (User) authentication.getPrincipal();
        List<Quiz> quizList;
        try {
            quizList = quizService.getAllQuiz(authenticationPrincipal.getId());
        } catch (QuizException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(quizList);
    }

    @Operation(summary = "Atualizar pontuação de um quiz", description = "Atualiza a pontuação de um quiz existente pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Quiz atualizado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Quiz.class))),
            @ApiResponse(responseCode = "404", description = "Quiz não encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateQuiz(@PathVariable(value = "id") String id,@RequestBody UpdateScoreRequest updateScoreRequest){
        Quiz quizModel;
        try {
            quizModel = quizService.updateQuiz(id, updateScoreRequest.score());
        } catch (QuizException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(quizModel);
    }

    @Operation(summary = "Excluir um quiz", description = "Exclui um quiz existente pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Quiz excluído com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "Quiz não encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteQuiz(@PathVariable(value = "id") String id){
        try {
            quizService.deleteQuiz(id);
        } catch (QuizException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body("Quiz successfully deleted!");
    }

}
