package com.Team22.preproject.StackOverFlow.comment.repository;

import com.Team22.preproject.StackOverFlow.comment.entity.QuestionComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionCommentRepository extends JpaRepository<QuestionComment, Long> {


    @Query(value = "select * from QUESTION_COMMENT where QUESTION_COMMENTS_ID = :questionCommentsId and MEMBER_ID = :memberId and QUESTION_ID = :questionId", nativeQuery = true)
    Optional<QuestionComment> finByIdAndQuestionIdAndMemberId(@Param("questionCommentsId") long questionCommentsId,
                                                              @Param("questionId") long questionId,
                                                              @Param("memberId") long memberId);

}
