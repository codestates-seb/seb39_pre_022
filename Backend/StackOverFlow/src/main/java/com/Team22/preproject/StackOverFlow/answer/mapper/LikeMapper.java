package com.Team22.preproject.StackOverFlow.answer.mapper;

import com.Team22.preproject.StackOverFlow.answer.dto.LikeRequestDto.SetLikeDto;
import com.Team22.preproject.StackOverFlow.answer.dto.LikeResponseDto;
import com.Team22.preproject.StackOverFlow.answer.dto.LikeResponseDto.LikeInfoDto;
import com.Team22.preproject.StackOverFlow.answer.entity.Answer;
import com.Team22.preproject.StackOverFlow.answer.entity.Like;
import org.mapstruct.Mapper;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@Mapper(componentModel = "spring")
public interface LikeMapper {


    public default Like createLikeDtoToLike(SetLikeDto setLikeDto){
        Like like = new Like();
        like.setVote(setLikeDto.getVote());  // vote를 먼저 지정해 줘야지 answer의 voteCount가 반영된다.

        Answer answer = new Answer();
        answer.setAnswerId(setLikeDto.getAnswerId());

        like.setMember(setLikeDto.getMember());
        like.setAnswer(answer);

        return like;
    }

    public default LikeInfoDto LikeToLikeInfoDto(Like like) {
        return LikeInfoDto.builder()
                .answerId(like.getAnswer().getAnswerId())
                .vote(like.getVote())
                .build();
    }

}
