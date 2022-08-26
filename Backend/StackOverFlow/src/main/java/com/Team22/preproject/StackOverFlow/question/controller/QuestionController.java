package com.Team22.preproject.StackOverFlow.question.controller;


import com.Team22.preproject.StackOverFlow.dto.response.SingleResponseWithMessageDto;
import com.Team22.preproject.StackOverFlow.member.entity.Member;
import com.Team22.preproject.StackOverFlow.question.dto.QuestionRequestDto;
import com.Team22.preproject.StackOverFlow.question.entity.Question;
import com.Team22.preproject.StackOverFlow.question.mapper.QuestionMapper;
import com.Team22.preproject.StackOverFlow.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.websocket.server.PathParam;

@Validated
@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;
    private final QuestionMapper mapper;

    @PostMapping
    public ResponseEntity createQuestion(@RequestBody @Valid QuestionRequestDto.CreatedQuestionDto createdQuestionDto){
        Question question = mapper.createQuestionDtoToQuestion(createdQuestionDto);
        questionService.createdQuestion(question);

        return new ResponseEntity<>(new SingleResponseWithMessageDto<>(mapper.createdDtoToQuestion(question),"CREATED"), HttpStatus.CREATED);
    }

    @GetMapping("{member-id}/question/{question-id}")
    public ResponseEntity getQuestion(@Positive @PathVariable("question-id") long questionId,
                                      @Positive @PathVariable("member-id") long memberId){
        Question question = questionService.findQuestion(questionId, memberId);

        return new ResponseEntity(new SingleResponseWithMessageDto<>(mapper.questionToInfo(question),"SUCCESS"),HttpStatus.OK);
    }
}
