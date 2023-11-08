package com.example.coders_battle.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.coders_battle.DTOs.AnswerDto;
import com.example.coders_battle.Service.Answer.AnswerService;

@RestController
@RequestMapping("/api/answer")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    public ResponseEntity<?> addAnswer(@RequestBody AnswerDto answerDto) {
        AnswerDto createdAnswerDto = answerService.postAnswer(answerDto);
        if (createdAnswerDto == null)
            return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdAnswerDto);
    }

}
