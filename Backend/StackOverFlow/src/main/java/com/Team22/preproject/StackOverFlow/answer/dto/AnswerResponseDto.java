package com.Team22.preproject.StackOverFlow.answer.dto;

import com.Team22.preproject.StackOverFlow.audit.Auditable;
import com.Team22.preproject.StackOverFlow.member.dto.MemberResponseDto;
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
    public static class CreateAnswerDto extends Auditable {
        private long answerId;
        private String answer;

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class AnswerInfo{
        private QuestionResponseDto.questionAnswerDto questionAnswerDto;
        private long answerId;
        private String answer;
    }
}
