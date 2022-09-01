package com.Team22.preproject.StackOverFlow.comment.dto;

import com.Team22.preproject.StackOverFlow.member.dto.MemberResponseDto.MemberComments;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class AnswerCommentResponseDto {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class AnswerCommentInfo {
        private long answerCommentId;
        private String answerComment;
        private MemberComments member;
    }
}
