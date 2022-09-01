package com.Team22.preproject.StackOverFlow.answer.repository;

import com.Team22.preproject.StackOverFlow.answer.entity.Answer;
import com.Team22.preproject.StackOverFlow.answer.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

    @Query(value = "select * from LIKES where ANSWER_ID = :answerId", nativeQuery = true)
    public List<Like> findByAnswerId(@Param("answerId") long answerId);


    @Query(value = "select * from LIKES where ANSWER_ID = :answerId and MEMBER_ID = :memberId", nativeQuery = true)
    Optional<Like> findByAnswerIdAndMemberId(@Param("memberId") long memberId, @Param("answerId") long answerId);

    @Query(value = "select sum(VOTE) from LIKES where ANSWER_ID = :answerId", nativeQuery = true)
    int findSumOfVoteByAnswerId(@Param("answerId") long answerId);


    @Query(value = "select * from LIKES where MEMBER_ID = :memberId", nativeQuery = true)
    Optional<Like> findByMemberId(@Param("memberId") long memberId);

}
