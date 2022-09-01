package com.Team22.preproject.StackOverFlow.comment.dto;

import com.Team22.preproject.StackOverFlow.answer.entity.Answer;
import com.Team22.preproject.StackOverFlow.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

public class AnswerCommentRequestDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class createAnswerCommentDto{
        private Member member;
        private long answerId;
        @NotBlank
        private String answerComments;

    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class updateAnswerCommentDto{
        private Member member;
        private long answerCommentsId;
        private long answerId;
        @NotBlank
        private String answerComments;

    }
}