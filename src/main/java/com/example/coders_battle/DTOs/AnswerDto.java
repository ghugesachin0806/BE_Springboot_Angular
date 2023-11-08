package com.example.coders_battle.DTOs;

import lombok.Data;

@Data
public class AnswerDto {

    private Long id;
    private String body;
    private Long questionId;
    private Long userId;
    
    
}
