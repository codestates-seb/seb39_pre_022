package com.Team22.preproject.StackOverFlow.answer.controller;

import com.Team22.preproject.StackOverFlow.answer.dto.AnswerRequestDto;
import com.Team22.preproject.StackOverFlow.answer.entity.Answer;
import com.Team22.preproject.StackOverFlow.answer.mapper.AnswerMapper;
import com.Team22.preproject.StackOverFlow.answer.service.AnswerService;
import com.Team22.preproject.StackOverFlow.dto.response.SingleResponseWithMessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;

@Validated
@RestController
@RequestMapping("questions/{question-id}/answer")
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerService answerService;
    private final AnswerMapper mapper;

    //답변 등록
    @PostMapping("/{member-id}")
    public ResponseEntity createdAnswer(@Positive @PathVariable("member-id") long memberId,
                                        @Positive @PathVariable("question-id") long questionId,
                                       @RequestBody AnswerRequestDto.CreateAnswerDto createAnswerDto){
        createAnswerDto.setMemberId(memberId);
        createAnswerDto.setQuestionId(questionId);
        Answer answer = answerService.createAnswer(mapper.createAnswerDtoToAnswer(createAnswerDto));
        return new ResponseEntity<>(new SingleResponseWithMessageDto<>(mapper.answerToAnswersInfo(answer), "CREATED"), HttpStatus.CREATED);
    }
}
