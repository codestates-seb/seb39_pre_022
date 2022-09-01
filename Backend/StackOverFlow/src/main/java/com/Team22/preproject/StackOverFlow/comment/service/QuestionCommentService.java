package com.Team22.preproject.StackOverFlow.comment.service;

import com.Team22.preproject.StackOverFlow.comment.entity.QuestionComment;
import com.Team22.preproject.StackOverFlow.comment.repository.QuestionCommentRepository;
import com.Team22.preproject.StackOverFlow.exception.BusinessLogicException;
import com.Team22.preproject.StackOverFlow.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestionCommentService {
    private final QuestionCommentRepository questionCommentRepository;

    public QuestionComment createComment(QuestionComment questionComment) {
        return questionCommentRepository.save(questionComment);
    }


    @Transactional(readOnly = true)
    public List<QuestionComment> findQuestionComments(long questionId) {
        return questionCommentRepository.findByQuestionId(questionId);
    }

    public QuestionComment updateQuestionComment(QuestionComment questionComment) {
        QuestionComment findQuestionComment = findQuestionComment(questionComment);
        ofNullable(questionComment.getQuestionComment()).ifPresent(findQuestionComment::setQuestionComment);
        return questionCommentRepository.save(findQuestionComment);
    }

    public List<QuestionComment> findPersonalQuestionComments(long questionId, long memberId) {
        return questionCommentRepository.findByQuestionIdAndMemberId(questionId, memberId);
    }

    @Transactional(readOnly = true)
    public QuestionComment findQuestionComment(QuestionComment questionComment) {
        Optional<QuestionComment> findAnswer = questionCommentRepository.findById(questionComment.getQuestionCommentId());
        return findAnswer
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.QUESTION_COMMENT_NOT_FOUND));
    }

    public void deleteQuestionComment(QuestionComment questionComment) {
        if (Exist(questionComment)) {
            questionCommentRepository.delete(questionComment);
        }

    }

    private boolean Exist(QuestionComment questionComment) {
        return !questionCommentRepository.findByQuestionId(questionComment.getQuestionCommentId()).isEmpty();
    }

}
