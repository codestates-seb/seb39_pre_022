package com.Team22.preproject.StackOverFlow.comment.controller;

import com.Team22.preproject.StackOverFlow.comment.dto.QuestionCommentRequestDto;
import com.Team22.preproject.StackOverFlow.comment.dto.QuestionCommentRequestDto.CreateQuestionCommentDto;
import com.Team22.preproject.StackOverFlow.comment.dto.QuestionCommentRequestDto.UpdateQuestionCommentDto;
import com.Team22.preproject.StackOverFlow.comment.entity.QuestionComment;
import com.Team22.preproject.StackOverFlow.comment.mapper.QuestionCommentMapper;
import com.Team22.preproject.StackOverFlow.comment.service.QuestionCommentService;
import com.Team22.preproject.StackOverFlow.dto.response.MessageResponseDto;
import com.Team22.preproject.StackOverFlow.dto.response.SingleResponseWithMessageDto;
import com.Team22.preproject.StackOverFlow.member.entity.Member;
import com.Team22.preproject.StackOverFlow.question.entity.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@Validated
@RestController
@RequestMapping("questions/{questionId}/comment")
@RequiredArgsConstructor
public class QuestionCommentController {
    private final QuestionCommentService questionCommentService;
    private final QuestionCommentMapper mapper;

    @PostMapping
    public ResponseEntity createQuestionComment(@SessionAttribute(name = SessionConst.LOGIN_MEMBER) Member member,
                                                @Positive @PathVariable(name="questionId") long questionId,
                                                @RequestBody @Valid CreateQuestionCommentDto createQuestionCommentDto){
        createQuestionCommentDto.setQuestionId(questionId);
        createQuestionCommentDto.setMember(member);
        QuestionComment questionComment = questionCommentService.createQuestionComment(mapper.createQuestionCommentDtoToComment(createQuestionCommentDto));
        return new ResponseEntity<>(new SingleResponseWithMessageDto<>(mapper.questionCommentToCommentInfo(questionComment),"SUCCESS"), HttpStatus.CREATED);
    }

    @PatchMapping("/{questionCommentId}")
    public ResponseEntity updateQuestionComment(@SessionAttribute(name = SessionConst.LOGIN_MEMBER) Member member,
                                                @Positive @PathVariable(name="questionId") long questionId,
                                                @Positive @PathVariable(name="questionCommentId") long questionCommentId,
                                                @RequestBody @Valid UpdateQuestionCommentDto updateQuestionCommentDto){

        updateQuestionCommentDto.setMember(member);
        updateQuestionCommentDto.setQuestionId(questionId);
        updateQuestionCommentDto.setQuestionCommentsId(questionCommentId);
        QuestionComment questionComment = questionCommentService.updateQuestionComment(mapper.updateQuestionCommentDtoToComment(updateQuestionCommentDto));
        return new ResponseEntity<>(new SingleResponseWithMessageDto<>(mapper.questionCommentToCommentInfo(questionComment),"SUCCESS"),HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{questionCommentId}")
    public MessageResponseDto deleteQuestionComment(@SessionAttribute(name = SessionConst.LOGIN_MEMBER) Member member,
                                                    @Positive @PathVariable(name="questionId") long questionId,
                                                    @Positive @PathVariable(name="questionCommentId") long questionCommentId)
    {
        questionCommentService.deleteQuestionComment(questionCommentId,questionId,member.getMemberId());
        return new MessageResponseDto("NO_CONTENT");
    }
}
