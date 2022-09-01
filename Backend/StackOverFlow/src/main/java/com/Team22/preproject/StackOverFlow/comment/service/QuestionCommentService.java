package com.Team22.preproject.StackOverFlow.comment.service;

import com.Team22.preproject.StackOverFlow.comment.controller.QuestionCommentController;
import com.Team22.preproject.StackOverFlow.comment.entity.QuestionComment;
import com.Team22.preproject.StackOverFlow.comment.repository.QuestionCommentRepository;
import com.Team22.preproject.StackOverFlow.exception.BusinessLogicException;
import com.Team22.preproject.StackOverFlow.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestionCommentService {
    private final QuestionCommentRepository questionCommentRepository;

    public QuestionComment createQuestionComment(QuestionComment questionComment) {
        return questionCommentRepository.save(questionComment);
    }

    public QuestionComment updateQuestionComment(QuestionComment questionComment) {
        QuestionComment findQuestionComment = findVerifiedQuestionComment(questionComment.getQuestionCommentsId(),
                questionComment.getQuestion().getQuestionId(),
                questionComment.getMember().getMemberId());
        Optional.ofNullable(questionComment.getQuestionComments()).ifPresent(findQuestionComment::setQuestionComments);
        return questionCommentRepository.save(findQuestionComment);
    }

    public void deleteQuestionComment(long questionCommentsId, long questionId, long memberId) {
        QuestionComment findQuestionComment = findVerifiedQuestionComment(questionCommentsId,questionId,memberId);
        questionCommentRepository.delete(findQuestionComment);
    }

    @Transactional(readOnly = true)
    public QuestionComment findVerifiedQuestionComment(long questionCommentId, long questionId, long memberId){
        Optional<QuestionComment> optionalQuestionComment = questionCommentRepository.finByIdAndQuestionIdAndMemberId(questionCommentId,questionId,memberId);
        return optionalQuestionComment.orElseThrow(()->new BusinessLogicException(ExceptionCode.QUESTION_COMMENT_NOT_FOUND));
    }

}
