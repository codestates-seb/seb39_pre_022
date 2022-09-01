package com.Team22.preproject.StackOverFlow.comment.controller;

import com.Team22.preproject.StackOverFlow.auth.SessionConst;
import com.Team22.preproject.StackOverFlow.comment.dto.QuestionCommentRequestDto;
import com.Team22.preproject.StackOverFlow.comment.dto.QuestionCommentRequestDto.CreateQuestionCommentDto;
import com.Team22.preproject.StackOverFlow.comment.dto.QuestionCommentRequestDto.UpdateQuestionCommentDto;
import com.Team22.preproject.StackOverFlow.comment.entity.QuestionComment;
import com.Team22.preproject.StackOverFlow.comment.mapper.QuestionCommentMapper;
import com.Team22.preproject.StackOverFlow.comment.service.QuestionCommentService;
import com.Team22.preproject.StackOverFlow.dto.response.MessageResponseDto;
import com.Team22.preproject.StackOverFlow.dto.response.SingleResponseDto;
import com.Team22.preproject.StackOverFlow.dto.response.SingleResponseWithMessageDto;
import com.Team22.preproject.StackOverFlow.member.entity.Member;
import com.Team22.preproject.StackOverFlow.question.entity.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import static com.Team22.preproject.StackOverFlow.auth.SessionConst.LOGIN_MEMBER;

@Validated
@RestController
@RequestMapping("questions/{questionId}/comment")
@RequiredArgsConstructor
public class QuestionCommentController {
    private final QuestionCommentService questionCommentService;
    private final QuestionCommentMapper mapper;

    @PostMapping
    public ResponseEntity createQuestionComment(@Positive long questionId,
                                                @RequestBody @Valid CreateQuestionCommentDto createQuestionCommentDto,
                                                @SessionAttribute(name= LOGIN_MEMBER) Member loginMember)
    {
        // binding
        createQuestionCommentDto.setQuestionId(questionId);
        createQuestionCommentDto.setMember(loginMember);

        QuestionComment questionComment = questionCommentService.createComment(mapper.createQuestionCommentDtoToComment(createQuestionCommentDto));

        return new ResponseEntity<>(new SingleResponseWithMessageDto<>(mapper.questionCommentToCommentInfo(questionComment),"SUCCESS"), HttpStatus.CREATED);
    }

    @PatchMapping("/questionCommentId")
    public ResponseEntity updateQuestionComment(@Positive long questionId,
                                                @Positive long questionCommentId,
                                                @RequestBody @Valid UpdateQuestionCommentDto updateQuestionCommentDto,
                                                @SessionAttribute(name= LOGIN_MEMBER) Member loginMember)
    {
        // binding
        updateQuestionCommentDto.setQuestionId(questionCommentId);
        updateQuestionCommentDto.setQuestionId(questionId);
        updateQuestionCommentDto.setMember(loginMember);

        QuestionComment questionComment = questionCommentService.updateQuestionComment(mapper.updateQuestionCommentDtoToQuestionComment(updateQuestionCommentDto));

        return new ResponseEntity<>(new SingleResponseDto<>(mapper.questionCommentToCommentInfo(questionComment)), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/questionCommentId")
    public MessageResponseDto deleteQuestionComment(@Positive long questionId,
                                                    @Positive long questionCommentId,
                                                    @SessionAttribute(name=LOGIN_MEMBER) Member loginMember)
    {
        QuestionComment questionComment = new QuestionComment();
        questionComment.setQuestionCommentId(questionCommentId);
        // question 참조
        Question question = new Question();
        question.setQuestionId(questionId);
        questionComment.setQuestion(question);
        // member 참조
        questionComment.setMember(loginMember);

        questionCommentService.deleteQuestionComment(questionComment);

        questionComment.setMember(null);
        questionComment.setQuestion(null);
        questionComment = null;

        return new MessageResponseDto("NO_CONTENT");
    }
}
