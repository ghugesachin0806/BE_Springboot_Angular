package com.example.coders_battle.Service.Answer;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.coders_battle.DTOs.AnswerDto;
import com.example.coders_battle.Entity.Answers;
import com.example.coders_battle.Entity.Questions;
import com.example.coders_battle.Entity.User;
import com.example.coders_battle.Repository.AnswerRepository;
import com.example.coders_battle.Repository.QuestionRepository;
import com.example.coders_battle.Repository.UserRepository;

import lombok.Data;

@Service
@Data
public class AnswerServiceImpl implements AnswerService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public AnswerDto postAnswer(AnswerDto answerDto) {
        Optional<User> optionalUser = userRepository.findById(answerDto.getUserId());
        Optional<Questions> optionalQuestion = questionRepository.findById(answerDto.getQuestionId());
        if(optionalUser.isPresent() && optionalQuestion.isPresent())
        {
            Answers answer = new Answers();
            answer.setBody(answerDto.getBody());
            answer.setCreatedDate(new Date());
            answer.setUser(optionalUser.get());
            answer.setQuestion(optionalQuestion.get());

            Answers createdAnswers = answerRepository.save(answer);
            AnswerDto createdAnswerDto = new AnswerDto();
            createdAnswerDto.setId(createdAnswers.getId());
            return createdAnswerDto;
        }

        return null;
    }

    
    
}
