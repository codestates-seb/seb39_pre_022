package com.Team22.preproject.StackOverFlow.question.service;

import com.Team22.preproject.StackOverFlow.exception.BusinessLogicException;
import com.Team22.preproject.StackOverFlow.exception.ExceptionCode;
import com.Team22.preproject.StackOverFlow.question.entity.Question;
import com.Team22.preproject.StackOverFlow.question.repository.QuestionRepository;
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
public class QuestionService {
    private final QuestionRepository questionRepository;

    public Question createdQuestion(Question question) {
        return questionRepository.save(question);

    }

    public Question updateQuestion(Question question) {
        Question findQuestion = findQuestion(question.getQuestionId(), question.getMember().getMemberId());
        Optional.ofNullable(question.getQuestion()).ifPresent(findQuestion::setQuestion);
        Optional.ofNullable(question.getTitle()).ifPresent(findQuestion::setTitle);
        return questionRepository.save(findQuestion);
    }


    @Transactional(readOnly = true)
    public Question findQuestion(long questionId, long memberId) {
        return findVerifiedQuestion(questionId, memberId);
    }

    public Page<Question> findMyQuestions(int page, int size, long memberId) {
        return questionRepository.findAllByMemberId(memberId, PageRequest.of(page, size, Sort.by("QUESTION_ID").descending()));
    }

    @Transactional(readOnly = true)
    public Question findVerifiedQuestion(long questionId, long memberId) {
        Optional<Question> optionalQuestion = questionRepository.finByQuestionIdAndMemberId(questionId,memberId);

        return optionalQuestion
                .orElseThrow(()->new BusinessLogicException(ExceptionCode.QUESTION_NOT_FOUND));
    }

    public void deleteQuestion(long questionId,long memberId) {
        Question question = findQuestion(questionId, memberId);
        questionRepository.delete(question);
    }

    public Page<Question> findQuestions(int page, int size) {
        return questionRepository.findAll(PageRequest.of(page, size));
    }

//    public Page<Question> findQuestionCreatedAt(Question question,int page, int size) {
//        return questionRepository.findAllByCreatedAt(question.getQuestionId(),PageRequest.of(page, size,Sort.Direction.DESC));
//    }

}
