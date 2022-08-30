package com.Team22.preproject.StackOverFlow.comment.repository;

import com.Team22.preproject.StackOverFlow.comment.entity.QuestionComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionCommentRepository extends JpaRepository<QuestionComment, Long> {
}
