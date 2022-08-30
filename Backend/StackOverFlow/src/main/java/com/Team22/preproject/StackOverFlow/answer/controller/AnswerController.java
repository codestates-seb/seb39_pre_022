package com.Team22.preproject.StackOverFlow.answer.controller;

import com.Team22.preproject.StackOverFlow.answer.dto.AnswerRequestDto;
import com.Team22.preproject.StackOverFlow.answer.entity.Answer;
import com.Team22.preproject.StackOverFlow.answer.mapper.AnswerMapper;
import com.Team22.preproject.StackOverFlow.answer.service.AnswerService;
import com.Team22.preproject.StackOverFlow.dto.response.MultiResponseWithPageInfoDto;
import com.Team22.preproject.StackOverFlow.dto.response.SingleResponseWithMessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import javax.websocket.server.PathParam;
import java.util.List;

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
        createAnswerDto.setQuestionId(questionId);
        createAnswerDto.setMemberId(memberId);
        Answer answer = answerService.createAnswer(mapper.createAnswerDtoToAnswer(createAnswerDto));
        return new ResponseEntity<>(new SingleResponseWithMessageDto<>(mapper.createAnswerDtoInfo(answer), "CREATED"), HttpStatus.CREATED);
    }

    //내가 쓴 질문에 대한 답변 전체 조회
    @GetMapping
    public ResponseEntity getAnswer( @Positive @PathVariable("question-id") long questionId,
                                     @Positive @PathParam("page") int page,
                                     @Positive @PathParam("size") int size){
        Page<Answer> pageOfAnswer = answerService.findAnswerList(questionId,page-1,size);
        List<Answer> answerList = pageOfAnswer.getContent();
        return new ResponseEntity(new MultiResponseWithPageInfoDto<>(mapper.answerListToAnswerInfoList(answerList),pageOfAnswer),HttpStatus.OK);
    }

    //답변 수정
    @PatchMapping("/{answer-id}")
    public ResponseEntity updateAnswer(@Positive @PathVariable("question-id") long questionId,
                                       @Positive @PathVariable("answer-id") long answerId,
                                       @RequestBody AnswerRequestDto.UpdateAnswerDto answerDto){
        answerDto.setAnswerId(answerId);
        answerDto.setQuestionId(questionId);
        Answer answer = answerService.updateAnswer(mapper.updateAnswerToAnswer(answerDto));
        return new ResponseEntity(new SingleResponseWithMessageDto<>(mapper.answerToAnswersInfo(answer),"SUCCESS"),HttpStatus.OK);

    }

    //답변 삭제
    @DeleteMapping("/{answer-id}")
    public ResponseEntity deleteAnswer(@Positive @PathVariable("question-id") long questionId,
                                       @Positive @PathVariable("answer-id") long answerId){
        answerService.deleteAnswer(answerId,questionId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}