package com.example.coders_battle.Service.Questions;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.coders_battle.DTOs.QuestionDTO;
import com.example.coders_battle.Entity.Questions;
import com.example.coders_battle.Entity.User;
import com.example.coders_battle.Repository.QuestionRepository;
import com.example.coders_battle.Repository.UserRepository;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public QuestionDTO addQuestion(QuestionDTO questionDTO) {
        Optional<User> optionalUser = userRepository.findById(questionDTO.getUserId());

        if (optionalUser.isPresent()) {
            Questions question = new Questions();

            question.setTitle(questionDTO.getTitle());
            question.setBody(questionDTO.getBody());
            question.setTags(questionDTO.getTags());
            question.setCreatedDate(new Date());

            Questions createdQuestion = questionRepository.save(question);

            QuestionDTO createdQuestionDTO = new QuestionDTO();
            createdQuestionDTO.setId(createdQuestion.getId());
            createdQuestionDTO.setTitle(createdQuestion.getTitle());

            return createdQuestionDTO;
        }

        return null;
    }

}
