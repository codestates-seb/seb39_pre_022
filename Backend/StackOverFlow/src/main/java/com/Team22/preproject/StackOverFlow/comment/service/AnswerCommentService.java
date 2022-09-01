package com.Team22.preproject.StackOverFlow.comment.service;

import com.Team22.preproject.StackOverFlow.answer.entity.Answer;
import com.Team22.preproject.StackOverFlow.comment.entity.AnswerComment;
import com.Team22.preproject.StackOverFlow.comment.repository.AnswerCommentRepository;
import com.Team22.preproject.StackOverFlow.exception.BusinessLogicException;
import com.Team22.preproject.StackOverFlow.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.*;

@Service
@Transactional
@RequiredArgsConstructor
public class AnswerCommentService {
    private final AnswerCommentRepository answerCommentRepository;

    public AnswerComment createAnswerComment(AnswerComment answerComment){
        return answerCommentRepository.save(answerComment);
    }

    @Transactional(readOnly = true)
    public List<AnswerComment> findAnswerComments(long answerId){
        return answerCommentRepository.findByAnswerId(answerId);
    }

    public AnswerComment updateAnswerComment(AnswerComment answerComment){
        AnswerComment findAnswer = findAnswerComment(answerComment);
        ofNullable(answerComment.getAnswerComment()).ifPresent(findAnswer::setAnswerComment);
        return answerCommentRepository.save(findAnswer);
    }

    public List<AnswerComment> findPersonalAnswerComments(long answerId, long memberId){
        return answerCommentRepository.findByAnswerIdAndMemberId(answerId, memberId);
    }

    @Transactional(readOnly = true)
    public AnswerComment findAnswerComment(AnswerComment answerComment){
        Optional<AnswerComment> findAnswer = answerCommentRepository.findById(answerComment.getAnswerCommentId());
        return findAnswer
                .orElseThrow(() ->  new BusinessLogicException(ExceptionCode.ANSWER_COMMENT_NOT_FOUND));
    }


    public void deleteAnswerComment(AnswerComment answerComment)
    {
        if(exist(answerComment))
            answerCommentRepository.delete(answerComment);
    }

    private boolean exist(AnswerComment answerComment) {
        return answerCommentRepository.findByAnswerIdAndMemberIdAndAnswerCommentId(answerComment.getAnswer().getAnswerId(), answerComment.getMember().getMemberId(), answerComment.getAnswerCommentId()) != null;
    }
}
