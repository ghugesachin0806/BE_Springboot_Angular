package com.example.coders_battle.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.coders_battle.DTOs.QuestionDTO;
import com.example.coders_battle.Service.Questions.QuestionService;

@RestController
@RequestMapping("/api/question")
public class QuestionsController {

    @Autowired
    private QuestionService questionService;

    @PostMapping
    public ResponseEntity<?> postQuestion(@RequestBody QuestionDTO questionDTO)
    {
        QuestionDTO createdQuestionDTO  = questionService.addQuestion(questionDTO);

        if(createdQuestionDTO==null)
        return new ResponseEntity<>("something went wrong",HttpStatus.BAD_REQUEST);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdQuestionDTO);
    }

}
