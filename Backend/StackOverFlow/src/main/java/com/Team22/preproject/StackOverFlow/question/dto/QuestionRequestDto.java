package com.Team22.preproject.StackOverFlow.question.dto;

import com.Team22.preproject.StackOverFlow.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class QuestionRequestDto {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CreatedQuestionDto{
        private Member member;

        @NotBlank
        private String title;

        @NotBlank
        private String question;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class UpdateQuestionDto{
        private long questionId;

        private String title;

        @NotBlank
        private String question;

        private Member member;
    }
}
