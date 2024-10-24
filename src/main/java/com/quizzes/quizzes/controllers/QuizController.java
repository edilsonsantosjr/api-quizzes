package com.quizzes.quizzes.controllers;

import com.quizzes.quizzes.dtos.QuizRequestDTO;
import com.quizzes.quizzes.dtos.UpdateScoreRequest;
import com.quizzes.quizzes.exceptions.QuizException;
import com.quizzes.quizzes.models.Quiz;
import com.quizzes.quizzes.models.User;
import com.quizzes.quizzes.services.OpenAIService;
import com.quizzes.quizzes.services.QuizServiceImpl;
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

    @GetMapping("/generate")
    public Mono<ResponseEntity<String>> generateQuiz(@RequestParam String topic){
        return openAIService.generateQuiz(topic)
                .map(ResponseEntity::ok)
                .onErrorResume(e -> Mono.just(ResponseEntity.badRequest().body("Error generating quiz: " + e.getMessage())));
    }

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
