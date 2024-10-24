package com.quizzes.quizzes.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OpenAIRequest {

    private String model;
    private List<Message> messageList;
    private double temperature;
    private int max_tokens;

    public OpenAIRequest(String model, List<Message> messageList, double temperature, int max_tokens) {
        this.model = model;
        this.messageList = messageList;
        this.temperature = temperature;
        this.max_tokens = max_tokens;
    }
}
