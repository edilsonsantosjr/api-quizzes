package com.quizzes.quizzes.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Option {

    private String a;
    private String b;
    private String c;
    private String d;

    public Option(String a, String b, String c, String d) {
        a = a;
        b = b;
        c = c;
        d = d;
    }
}
