package com.example.coders_battle.DTOs;

import java.util.List;

import lombok.Data;

@Data
public class AllQuestionResponseDto {

    private List<QuestionDTO> questionDTOList;

    private Integer totalPges;

    private Integer pageNumber;

}
