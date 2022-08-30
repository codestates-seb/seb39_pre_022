package com.Team22.preproject.StackOverFlow.comment.controller;

import com.Team22.preproject.StackOverFlow.comment.dto.QuestionCommentRequestDto;
import com.Team22.preproject.StackOverFlow.comment.entity.QuestionComment;
import com.Team22.preproject.StackOverFlow.comment.mapper.QuestionCommentMapper;
import com.Team22.preproject.StackOverFlow.comment.service.QuestionCommentService;
import com.Team22.preproject.StackOverFlow.dto.response.SingleResponseWithMessageDto;
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

    @PostMapping("/{member-id}")
    public ResponseEntity createQComment(@Positive @PathVariable("member-id") long memberId,
                                         @Positive @PathVariable("question-id") long questionId,
                                         @RequestBody @Valid QuestionCommentRequestDto.CreateQCommentDto createQCommentDto){
        createQCommentDto.setQuestionId(questionId);
        createQCommentDto.setMemberId(memberId);
        QuestionComment questionComment = questionCommentService.createComment(mapper.createQuestionCommentDtoToComment(createQCommentDto));
        return new ResponseEntity<>(new SingleResponseWithMessageDto<>(mapper.questionCommentToCommentInfo(questionComment),"SUCCESS"), HttpStatus.CREATED);
    }
}
