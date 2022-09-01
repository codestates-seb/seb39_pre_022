package com.Team22.preproject.StackOverFlow.answer.dto;

import com.Team22.preproject.StackOverFlow.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AnswerRequestDto {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CreateAnswerDto {
        private long questionId;

        private Member member;

        @NotBlank
        private String answer;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class UpdateAnswerDto{
        private long answerId;
        private long questionId;

        private Member member;

        @NotBlank
        private String answer;
    }
}
