package com.quizzes.quizzes.services;

import com.quizzes.quizzes.models.Message;
import com.quizzes.quizzes.models.OpenAIRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class OpenAIService {

    private final WebClient webClient;

    @Value("${openai.api.key}")
    private String apiKey;

    public OpenAIService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.openai.com/v1/chat/completions").build();
    }

    public Mono<String> generateQuiz(String topic){
        String prompt = "Generate a quiz with 10 multiple-choice questions about [topic]. For each question, provide 4 answer options labeled A, B, C, and D, and indicate which one is the correct answer. Also, add a title and description to the object and all the text in pt-br.";

        return this.webClient.post()
                .header("Authorization", "Bearer " + apiKey)
                .bodyValue(buildRequest(prompt))
                .retrieve()
                .bodyToMono(String.class);
    }

    private OpenAIRequest buildRequest(String prompt) {
        return new OpenAIRequest(
                "gpt-3.5-turbo",
                new ArrayList<>(List.of(new Message("user", prompt))),
                0.7,
                1000
        );
    }
}
