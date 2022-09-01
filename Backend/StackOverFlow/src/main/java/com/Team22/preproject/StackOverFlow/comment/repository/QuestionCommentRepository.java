package com.Team22.preproject.StackOverFlow.comment.repository;

import com.Team22.preproject.StackOverFlow.comment.entity.QuestionComment;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionCommentRepository extends JpaRepository<QuestionComment, Long> {

    @Query(value = "select * from QUESTIONCOMMENT where QUESTION_ID = :questionId", nativeQuery = true)
    public List<QuestionComment> findByQuestionId(@Param("questionId")long questionId);

    @Query(value = "select * from QUESTIONCOMMENT where ANSWER_ID = :questionId and MEMBER_ID = :memberId", nativeQuery = true)
    public List<QuestionComment> findByQuestionIdAndMemberId(@Param("questionId")long questionId, @Param("memberId") long memberId);

    @Query(value = "select * from QUESTIONCOMMENT where MEMBER_ID = :memberId", nativeQuery = true)
    public List<QuestionComment> findByMemberId(@Param("memberId")long memberId);
}
