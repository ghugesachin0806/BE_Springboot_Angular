package com.example.coders_battle.Service.Questions;

import java.util.Date;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.example.coders_battle.DTOs.SingleQuestionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.coders_battle.DTOs.AllQuestionResponseDto;
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

    public static final int SEARCH_RESULT_PER_PAGE=5;

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

    @Override
    public AllQuestionResponseDto getAllQuestions(int pageNumber) {
        
        Pageable paging = PageRequest.of(pageNumber, SEARCH_RESULT_PER_PAGE);
        Page<Questions> questionspage = questionRepository.findAll(paging);

        AllQuestionResponseDto allQuestionResponseDto = new AllQuestionResponseDto();
        allQuestionResponseDto.setQuestionDTOList(questionspage.getContent().stream().map(Questions::getQuestionDTO).collect(Collectors.toList()));

        allQuestionResponseDto.setPageNumber(questionspage.getPageable().getPageNumber());
        allQuestionResponseDto.setTotalPges(questionspage.getTotalPages());

        return allQuestionResponseDto;
    }

    @Override
    public SingleQuestionDTO getQuestionById(Long questionId) {
        Optional<Questions> optionalQuestions = questionRepository.findById(questionId);
        SingleQuestionDTO singleQuestionDTO = new SingleQuestionDTO();
        optionalQuestions.ifPresent(questions -> singleQuestionDTO.setQuestionDTO(questions.getQuestionDTO()));
        return singleQuestionDTO;
    }

}
