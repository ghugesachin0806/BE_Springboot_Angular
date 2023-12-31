package com.example.coders_battle.Service.Questions;

import com.example.coders_battle.DTOs.AllQuestionResponseDto;
import com.example.coders_battle.DTOs.QuestionDTO;
import com.example.coders_battle.DTOs.SingleQuestionDTO;

public interface QuestionService {

    QuestionDTO addQuestion(QuestionDTO questionDTO);

    AllQuestionResponseDto getAllQuestions(int pageNumber);

    SingleQuestionDTO getQuestionById(Long questionId);
}
