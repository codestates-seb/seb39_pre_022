package com.Team22.preproject.StackOverFlow.answer.controller;

import com.Team22.preproject.StackOverFlow.answer.dto.LikeRequestDto.SetLikeDto;
import com.Team22.preproject.StackOverFlow.answer.entity.Like;
import com.Team22.preproject.StackOverFlow.answer.mapper.LikeMapper;
import com.Team22.preproject.StackOverFlow.answer.service.LikeService;
import com.Team22.preproject.StackOverFlow.auth.SessionConst;
import com.Team22.preproject.StackOverFlow.dto.response.SingleResponseWithMessageDto;
import com.Team22.preproject.StackOverFlow.member.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
// 다시 병합
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/questions/answer/{answerId}")
public class LikeController {

    private final LikeService likeService;
    private final LikeMapper mapper;

    @PostMapping
    public ResponseEntity upVote(@SessionAttribute(name= SessionConst.LOGIN_MEMBER)Member loginMember,
                                 @PathVariable long answerId)
    {
        SetLikeDto setLikeDto = SetLikeDto.builder()
                .member(loginMember)
                .vote((byte)1)
                .answerId(answerId).build();

        Like like = likeService.setLike(mapper.createLikeDtoToLike(setLikeDto));

        return new ResponseEntity(new SingleResponseWithMessageDto<>(mapper.LikeToLikeInfoDto(like), "success"),HttpStatus.OK);

    }


    @DeleteMapping
    public ResponseEntity downVote(@SessionAttribute(name= SessionConst.LOGIN_MEMBER)Member loginMember,
                                   @PathVariable long answerId)
    {
        SetLikeDto setLikeDto = SetLikeDto.builder()
                .member(loginMember)
                .vote((byte)-1)
                .answerId(answerId).build();

        Like like = likeService.setLike(mapper.createLikeDtoToLike(setLikeDto));

        return new ResponseEntity(new SingleResponseWithMessageDto<>(mapper.LikeToLikeInfoDto(like), "success"),HttpStatus.OK);
    }
}
