package com.Team22.preproject.StackOverFlow.answer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class LikeResponseDto {

    @NoArgsConstructor
    @Data
    @Builder
    @AllArgsConstructor
    public static class LikeInfoDto{
        private long answerId;
        private byte vote;
    }
}
