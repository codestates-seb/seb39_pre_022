package com.Team22.preproject.StackOverFlow.question.service;

import com.Team22.preproject.StackOverFlow.exception.BusinessLogicException;
import com.Team22.preproject.StackOverFlow.exception.ExceptionCode;
import com.Team22.preproject.StackOverFlow.question.entity.Question;
import com.Team22.preproject.StackOverFlow.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    public Question createdQuestion(Question question) {
        return questionRepository.save(question);

    }

    @Transactional(readOnly = true)
    public Question findQuestion(long questionId, long memberId) {
        return findVerifiedQuestion(questionId, memberId);
    }


    @Transactional(readOnly = true)
    public Question findVerifiedQuestion(long questionId, long memberId) {
        Optional<Question> optionalQuestion = questionRepository.finByIdAndMemberId(questionId,memberId);

        return optionalQuestion
                .orElseThrow(()->new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));
    }


}
