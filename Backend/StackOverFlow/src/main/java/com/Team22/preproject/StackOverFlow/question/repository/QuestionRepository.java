package com.Team22.preproject.StackOverFlow.question.repository;

import com.Team22.preproject.StackOverFlow.question.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query(value = "select * " +
    "from QUESTION " +
    "inner join QUESTION " +
    "on QUESTION_ID = :questionId " +
    "where 1 = 1 " +
    "and QUESTION_ID = :questionId " +
    "and MEMBER_ID = :memberId", nativeQuery = true)
    Optional<Question> finByIdAndMemberId(@Param("questionId") long questionId,@Param("memberId") long memberId);
}
