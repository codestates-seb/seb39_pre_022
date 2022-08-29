package com.Team22.preproject.StackOverFlow.answer.dto;

import com.Team22.preproject.StackOverFlow.question.dto.QuestionResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class AnswerResponseDto {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class AnswerInfo{
        private QuestionResponseDto.QuestionInfo questionInfo;
        private long answerId;
        private String answer;
    }
}
