package com.Team22.preproject.StackOverFlow.answer.service;

import com.Team22.preproject.StackOverFlow.answer.entity.Answer;
import com.Team22.preproject.StackOverFlow.answer.repository.AnswerRepository;
import com.Team22.preproject.StackOverFlow.exception.BusinessLogicException;
import com.Team22.preproject.StackOverFlow.exception.ExceptionCode;
import com.Team22.preproject.StackOverFlow.question.entity.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;

    public Answer createAnswer(Answer answer) {
        return answerRepository.save(answer);
    }



    public Answer updateAnswer(Answer answer) {
        Answer findAnswer = findAnswer(answer.getAnswerId(),answer.getQuestion().getQuestionId());
        Optional.ofNullable(answer.getAnswer()).ifPresent(findAnswer::setAnswer);
        return answerRepository.save(findAnswer);
    }

    @Transactional(readOnly = true)
    public Answer findVerifiedAnswer(long memberId, long questionId, long answerId){
        Optional<Answer> optionalAnswer
                = answerRepository.findByIdAndAnswerIdAndQuestionId(memberId, questionId,answerId);
        return optionalAnswer.orElseThrow(() -> new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public Answer findAnswer(long answerId,long questionId) {
        return findVerifiedAnswer(answerId,questionId);
    }

    @Transactional(readOnly = true)
    public Answer findVerifiedAnswer(long answerId,long questionId) {
        Optional<Answer> optionalAnswer = answerRepository.finByQuestionIdAndAnswerId(answerId,questionId);

        return optionalAnswer
                .orElseThrow(()->new BusinessLogicException(ExceptionCode.ANSWER_NOT_FOUND));
    }

    public Page<Answer> findAnswerList(long questionId, int page, int size) {
        return answerRepository.finaAllByAnswerListId(questionId, PageRequest.of(page, size, Sort.by("ANSWER_ID").descending()));
    }

    public void deleteAnswer(long answerId, long questionId) {
        Answer answer = findAnswer(answerId,questionId);
        answerRepository.delete(answer);
    }
}
