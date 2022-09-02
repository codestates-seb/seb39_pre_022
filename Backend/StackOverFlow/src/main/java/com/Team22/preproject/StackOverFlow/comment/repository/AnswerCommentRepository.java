package com.Team22.preproject.StackOverFlow.comment.repository;

import com.Team22.preproject.StackOverFlow.comment.entity.AnswerComment;
import com.Team22.preproject.StackOverFlow.comment.entity.QuestionComment;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnswerCommentRepository extends JpaRepository<AnswerComment, Long> {
    @Query(value = "select * from ANSWER_COMMENT where ANSWER_ID = :answerId", nativeQuery = true)
    public List<AnswerComment> findByAnswerId(@Param("answerId")long answerId);
    @Query(value = "select * from ANSWER_COMMENT where ANSWER_ID = :answerId and MEMBER_ID = :memberId", nativeQuery = true)
    public List<AnswerComment> findByAnswerIdAndMemberId(@Param("answerId")long answerId, @Param("memberId") long memberId);

    @Query(value = "select * from ANSWER_COMMENT where ANSWER_ID = :answerId and MEMBER_ID = :memberId and ANSWER_COMMENT_ID = :answerCommentId", nativeQuery = true)
    public AnswerComment findByAnswerIdAndMemberIdAndAnswerCommentId(@Param("answerId")long answerId, @Param("memberId") long memberId, @Param("answerCommentId") long answerCommentId);

    @Query(value = "select * from ANSWER_COMMENT where MEMBER_ID = :memberId", nativeQuery = true)
    public List<AnswerComment> findByMemberId(@Param("memberId")long memberId);

}
