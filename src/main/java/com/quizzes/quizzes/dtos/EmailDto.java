package com.quizzes.quizzes.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailDto {

    private String userId;
    private String emailTo;
    private String title;
    private String text;

}
