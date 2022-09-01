package com.Team22.preproject.StackOverFlow.comment.controller;

import com.Team22.preproject.StackOverFlow.answer.entity.Answer;
import com.Team22.preproject.StackOverFlow.comment.dto.AnswerCommentRequestDto.createAnswerCommentDto;
import com.Team22.preproject.StackOverFlow.comment.dto.AnswerCommentRequestDto.updateAnswerCommentDto;
import com.Team22.preproject.StackOverFlow.comment.entity.AnswerComment;
import com.Team22.preproject.StackOverFlow.comment.mapper.AnswerCommentMapper;
import com.Team22.preproject.StackOverFlow.comment.service.AnswerCommentService;
import com.Team22.preproject.StackOverFlow.dto.response.MessageResponseDto;
import com.Team22.preproject.StackOverFlow.dto.response.SingleResponseDto;
import com.Team22.preproject.StackOverFlow.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;

import static com.Team22.preproject.StackOverFlow.auth.SessionConst.LOGIN_MEMBER;


@RestController
@Validated
@RequestMapping("answers/{answerId}/comment")
@RequiredArgsConstructor
public class AnswerCommentController {

    private final AnswerCommentService answerCommentService;
    private final AnswerCommentMapper mapper;


    @PostMapping
    public ResponseEntity createAnswerComment(@Positive @PathVariable long answerId,
                                              @RequestBody createAnswerCommentDto createAnswerCommentDto,
                                              @SessionAttribute(name= LOGIN_MEMBER) Member loginMember )
    {
        createAnswerCommentDto.setAnswerId(answerId);
        createAnswerCommentDto.setMember(loginMember);
        AnswerComment answerComment = answerCommentService.createAnswerComment(mapper.createAnswerCommentDtoToAnswerComment(createAnswerCommentDto));

        return new ResponseEntity(new SingleResponseDto<>(mapper.answerCommentToAnswerCommentInfo(answerComment)), HttpStatus.CREATED);
    }



    @PatchMapping("/{answerCommentId}")
    public ResponseEntity updateAnswerComment(@Positive @PathVariable long answerId,
                                              @Positive @PathVariable long answerCommentId,
                                              @RequestBody updateAnswerCommentDto updateAnswerCommentDto,
                                              @SessionAttribute(name= LOGIN_MEMBER) Member loginMember )
    {
        updateAnswerCommentDto.setAnswerId(answerId);
        updateAnswerCommentDto.setAnswerCommentId(answerCommentId);
        updateAnswerCommentDto.setMember(loginMember);

        AnswerComment answerComment = answerCommentService.updateAnswerComment(mapper.updateAnswerCommentDtoToAnswerComment(updateAnswerCommentDto));

        return new ResponseEntity(new SingleResponseDto<>(mapper.answerCommentToAnswerCommentInfo(answerComment)), HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{answerCommentId}")
    public MessageResponseDto deleteAnswerComment(@Positive @PathVariable long answerId,
                                                  @Positive @PathVariable long answerCommentId,
                                                  @SessionAttribute(name=LOGIN_MEMBER) Member loginMember)
    {
        AnswerComment answerComment = new AnswerComment();
        Answer answer = new Answer();
        answer.setAnswerId(answerId);
        answerComment.setAnswerCommentId(answerCommentId);
        answerComment.setMember(loginMember);

        answerCommentService.deleteAnswerComment(answerComment);

        answerComment.setAnswer(null);
        answerComment.setMember(null);
        answerComment = null;

        return new MessageResponseDto("NO_CONTENT");
    }
}
