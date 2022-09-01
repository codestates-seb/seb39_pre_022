package com.Team22.preproject.StackOverFlow.comment.dto;

import com.Team22.preproject.StackOverFlow.member.dto.MemberResponseDto;
import com.Team22.preproject.StackOverFlow.question.dto.QuestionResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class QuestionCommentResponseDto {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class QuestionCommentsInfo {
        private long questionCommentsId;
        private String questionComments;
        private MemberResponseDto.MemberComments member;
    }
}
