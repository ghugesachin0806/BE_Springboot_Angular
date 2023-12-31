package com.example.coders_battle.Controllers;

import com.example.coders_battle.DTOs.SingleQuestionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.coders_battle.DTOs.AllQuestionResponseDto;
import com.example.coders_battle.DTOs.QuestionDTO;
import com.example.coders_battle.Service.Questions.QuestionService;

@RestController
@RequestMapping("/api")
public class QuestionsController {

    @Autowired
    private QuestionService questionService;

    @PostMapping("/question")
    public ResponseEntity<?> postQuestion(@RequestBody QuestionDTO questionDTO)
    {
        QuestionDTO createdQuestionDTO  = questionService.addQuestion(questionDTO);

        if(createdQuestionDTO==null)
        return new ResponseEntity<>("something went wrong",HttpStatus.BAD_REQUEST);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdQuestionDTO);
    }

    @GetMapping("/questions/{pageNumber}")
    public ResponseEntity<AllQuestionResponseDto> getAllQuestions(@PathVariable("pageNumber") int pageNumber)
    {
        AllQuestionResponseDto allQuestionResponseDto = questionService.getAllQuestions(pageNumber);
        return ResponseEntity.ok(allQuestionResponseDto);
    }

    @GetMapping("/questions/{questionId}")
    public  ResponseEntity<?> getQuestionById(@PathVariable("questionId") Long questionId)
    {
        SingleQuestionDTO singleQuestionDTO= questionService.getQuestionById(questionId);

        if(singleQuestionDTO==null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(singleQuestionDTO);
    }

}
