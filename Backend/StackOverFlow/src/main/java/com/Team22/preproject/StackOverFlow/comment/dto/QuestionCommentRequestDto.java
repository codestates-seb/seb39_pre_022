package com.Team22.preproject.StackOverFlow.comment.dto;

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
    public static class CreateQCommentDto {
        private long memberId;
        private long questionId;
        @NotEmpty
        private String questionComments;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class UpdateQCommentDto {
        private long questionId;
        private long memberId;
        @NotEmpty
        private String questionComments;
    }

}
