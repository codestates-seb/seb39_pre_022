package com.Team22.preproject.StackOverFlow.comment.service;

import com.Team22.preproject.StackOverFlow.comment.controller.QuestionCommentController;
import com.Team22.preproject.StackOverFlow.comment.entity.QuestionComment;
import com.Team22.preproject.StackOverFlow.comment.repository.QuestionCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestionCommentService {
    private final QuestionCommentRepository questionCommentRepository;

    public QuestionComment createComment(QuestionComment questionComment) {
        return questionCommentRepository.save(questionComment);
    }
}
