package com.Team22.preproject.StackOverFlow.comment.controller;

import com.Team22.preproject.StackOverFlow.comment.dto.QuestionCommentRequestDto;
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
                                                @Positive @PathVariable long questionId,
                                                @RequestBody @Valid QuestionCommentRequestDto.CreateQCommentDto createQCommentDto){
        createQCommentDto.setQuestionId(questionId);
        createQCommentDto.setMember(member);
        QuestionComment questionComment = questionCommentService.createQuestionComment(mapper.createQuestionCommentDtoToComment(createQCommentDto));
        return new ResponseEntity<>(new SingleResponseWithMessageDto<>(mapper.questionCommentToCommentInfo(questionComment),"SUCCESS"), HttpStatus.CREATED);
    }

    @PatchMapping("/{questionCommentId}")
    public ResponseEntity updateQuestionComment(@SessionAttribute(name = SessionConst.LOGIN_MEMBER) Member member,
                                                @Positive @PathVariable long questionId,
                                                @Positive @PathVariable long questionCommentId,
                                                @RequestBody @Valid QuestionCommentRequestDto.UpdateQCommentDto updateQCommentDto){

        updateQCommentDto.setMember(member);
        updateQCommentDto.setQuestionId(questionId);
        updateQCommentDto.setQuestionCommentsId(questionCommentId);
        QuestionComment questionComment = questionCommentService.updateQuestionComment(mapper.updateQuestionCommentDtoToComment(updateQCommentDto));
        return new ResponseEntity<>(new SingleResponseWithMessageDto<>(mapper.questionCommentToCommentInfo(questionComment),"SUCCESS"),HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{questionCommentId}")
    public MessageResponseDto deleteQuestionComment(@SessionAttribute(name = SessionConst.LOGIN_MEMBER) Member member,
                                                    @Positive @PathVariable long questionId,
                                                    @Positive @PathVariable long questionCommentId)
    {
        questionCommentService.deleteQuestionComment(questionCommentId,questionId,member.getMemberId());
        return new MessageResponseDto("NO_CONTENT");
    }
}
