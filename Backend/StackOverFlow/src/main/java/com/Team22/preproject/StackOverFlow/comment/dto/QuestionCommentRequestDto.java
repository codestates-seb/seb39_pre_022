package com.Team22.preproject.StackOverFlow.comment.dto;

import com.Team22.preproject.StackOverFlow.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

public class QuestionCommentRequestDto {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CreateQuestionCommentDto {
        private Member member;
        private long questionId;
        @NotEmpty
        private String questionComment;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class UpdateQuestionCommentDto {
        private long questionId;
        private long questionCommentId;
        private Member member;
        @NotEmpty
        private String questionComment;
    }

}
