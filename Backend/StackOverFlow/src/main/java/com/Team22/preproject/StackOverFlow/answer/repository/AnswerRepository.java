package com.Team22.preproject.StackOverFlow.answer.repository;

import com.Team22.preproject.StackOverFlow.answer.entity.Answer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {


    @Query(value = "select * from ANSWER where ANSWER_ID = :answerId and MEMBER_ID = :memberId and QUESTION_ID = :questionId",nativeQuery = true)
    Optional<Answer> findByIdAndAnswerIdAndQuestionId(@Param("memberId") long memberId, @Param("questionId")long questionId, @Param("answerId") long answerId);

    @Query(value = "select * from ANSWER where QUESTION_ID = :questionId",nativeQuery = true)
    Page<Answer> finaAllByAnswerListId(@Param("questionId") long questionId, Pageable pageable);

    @Query(value = "select " +
            "* " +
            "from ANSWER " +
            "where " +
            "ANSWER_ID = :answerId " +
            "and QUESTION_ID = :questionId", nativeQuery = true)
    Optional<Answer> finByQuestionIdAndAnswerId(@Param("answerId") long answerId, @Param("questionId") long questionId);
}
