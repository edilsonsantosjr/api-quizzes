package com.quizzes.quizzes.producers;

import com.quizzes.quizzes.dtos.EmailDto;
import com.quizzes.quizzes.models.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserProducer {

    final RabbitTemplate rabbitTemplate;

    public UserProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value(value = "${broker.queue.email.name}")
    private String routingKey;

    public void publishMessageEmail(User user){
        var emailDto = new EmailDto();
        emailDto.setUserId(user.getId());
        emailDto.setEmailTo(user.getEmail());
        emailDto.setTitle("Cadastro realizado com sucesso!");
        emailDto.setText(user.getName() + ", seja bem-vindo(a)!");

        rabbitTemplate.convertAndSend("", routingKey, emailDto);
    }
}
