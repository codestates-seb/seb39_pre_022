package com.Team22.preproject.StackOverFlow.answer.service;

import com.Team22.preproject.StackOverFlow.answer.entity.Like;
import com.Team22.preproject.StackOverFlow.answer.mapper.LikeMapper;
import com.Team22.preproject.StackOverFlow.answer.repository.LikeRepository;
import com.Team22.preproject.StackOverFlow.exception.BusinessLogicException;
import com.Team22.preproject.StackOverFlow.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;


    public Like createLike(Like like) {
        return likeRepository.save(like);
    }

    public Like setLike(Like like){

        Like targetLike = findLike(like);
        if(targetLike == null) {
            return createLike(like);
        }

        targetLike.setVote(like.getVote());
        return likeRepository.save(targetLike);

    }

    public int sumOfVotes(Like like) {
        return likeRepository.findSumOfVoteByAnswerId(like.getAnswer().getAnswerId());
    }

    public Like findLike(Like like) {
        return findVerifiedLike(like.getAnswer().getAnswerId(), like.getMember().getMemberId());
    }

    @Transactional(readOnly = true)
    public Like findVerifiedLike(long answerId, long memberId){
        Optional<Like> optionalLike = likeRepository.findByAnswerIdAndMemberId(answerId, memberId);
        return optionalLike.get();
    }

}
