package com.Team22.preproject.StackOverFlow.question.dto;

import com.Team22.preproject.StackOverFlow.answer.dto.AnswerResponseDto;
import com.Team22.preproject.StackOverFlow.audit.Auditable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class QuestionResponseDto {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CreateQuestionDto extends Auditable {
        private long questionId;

        private String question;

        private String title;

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class QuestionInfoList{
        private long questionId;
        private long memberId;
        private String title;
        private String question;
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class QuestionInfo{
        private long questionId;
        private String title;
        private String question;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class UpdateQuestionDto{
        private long questionId;

        private String title;

        private String question;

    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class questionAnswerDto{
        private long questionId;

        private String nickName;

        private String title;
        private String question;
    }

}
