package com.Team22.preproject.StackOverFlow.question.repository;

import com.Team22.preproject.StackOverFlow.question.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.PageRequest;


import javax.websocket.server.PathParam;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
//    Page<Question> findAllByCreatedAt(Pageable pageable);

    @Query(value = "select " +
    "* " +
    "from QUESTION " +
    "where 1 = 1 " +
    "and QUESTION_ID = :questionId " +
    "and MEMBER_ID = :memberId", nativeQuery = true)
    Optional<Question> finByQuestionIdAndMemberId(@Param("questionId") long questionId,@Param("memberId") long memberId);

    @Query(value = "select * from QUESTION where MEMBER_ID = :memberId",nativeQuery = true)
    Page<Question> findAllByMemberId(@Param("memberId") long memberId, Pageable pageable);

//    @Query(value = "select * from QUESTION where QUESTION_ID = :questionId", nativeQuery = true)
//    Page<Question> findAllByCreatedAt(long questionId, Pageable pageable);
}
