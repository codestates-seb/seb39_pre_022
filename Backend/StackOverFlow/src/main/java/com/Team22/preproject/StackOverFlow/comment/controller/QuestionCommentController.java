package com.Team22.preproject.StackOverFlow.comment.controller;

import com.Team22.preproject.StackOverFlow.comment.dto.QuestionCommentRequestDto;
import com.Team22.preproject.StackOverFlow.comment.entity.QuestionComment;
import com.Team22.preproject.StackOverFlow.comment.mapper.QuestionCommentMapper;
import com.Team22.preproject.StackOverFlow.comment.service.QuestionCommentService;
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
@RequestMapping("questions/{question-id}/comment")
@RequiredArgsConstructor
public class QuestionCommentController {
    private final QuestionCommentService questionCommentService;
    private final QuestionCommentMapper mapper;

    @PostMapping()
    public ResponseEntity createQComment(@SessionAttribute(name = SessionConst.LOGIN_MEMBER) Member member,
                                         @Positive @PathVariable("question-id") long questionId,
                                         @RequestBody @Valid QuestionCommentRequestDto.CreateQCommentDto createQCommentDto){
        createQCommentDto.setQuestionId(questionId);
        createQCommentDto.setMemberId(member.getMemberId());
        QuestionComment questionComment = questionCommentService.createQuestionComment(mapper.createQuestionCommentDtoToComment(createQCommentDto));
        return new ResponseEntity<>(new SingleResponseWithMessageDto<>(mapper.questionCommentToCommentInfo(questionComment),"SUCCESS"), HttpStatus.CREATED);
    }

    @PatchMapping("/{question-comments-id}")
    public ResponseEntity updateQComment(@SessionAttribute(name = SessionConst.LOGIN_MEMBER) Member member,
                                         @Positive @PathVariable("question-id") long questionId,
                                         @Positive @PathVariable("question-comments-id") long questionCommentsId,
                                         @RequestBody @Valid QuestionCommentRequestDto.UpdateQCommentDto updateQCommentDto){
        updateQCommentDto.setMemberId(member.getMemberId());
        updateQCommentDto.setQuestionId(questionId);
        updateQCommentDto.setQuestionCommentsId(questionCommentsId);
        QuestionComment questionComment = questionCommentService.updateQuestionComment(mapper.updateQuestionCommentDtoToComment(updateQCommentDto));
        return new ResponseEntity<>(new SingleResponseWithMessageDto<>(mapper.questionCommentToCommentInfo(questionComment),"SUCCESS"),HttpStatus.OK);
    }

    @DeleteMapping("/{question-comments-id}")
    public ResponseEntity deleteQComment(@SessionAttribute(name = SessionConst.LOGIN_MEMBER) Member member,
                                         @Positive @PathVariable("question-id") long questionId,
                                         @Positive @PathVariable("question-comments-id") long questionCommentsId){
        questionCommentService.deleteQuestionComment(questionCommentsId,questionId,member.getMemberId());
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
