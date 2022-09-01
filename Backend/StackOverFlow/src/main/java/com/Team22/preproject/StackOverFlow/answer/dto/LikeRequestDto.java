package com.Team22.preproject.StackOverFlow.answer.dto;

import com.Team22.preproject.StackOverFlow.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class LikeRequestDto {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class SetLikeDto {
        private long answerId;
        private Member member; // 그냥 바로 관계를 맺어준다.
        private byte vote;
    }

}
