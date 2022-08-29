package com.Team22.preproject.StackOverFlow.answer.mapper;

import com.Team22.preproject.StackOverFlow.answer.dto.AnswerRequestDto;
import com.Team22.preproject.StackOverFlow.answer.dto.AnswerResponseDto;
import com.Team22.preproject.StackOverFlow.answer.entity.Answer;
import com.Team22.preproject.StackOverFlow.member.dto.MemberResponseDto;
import com.Team22.preproject.StackOverFlow.member.entity.Member;
import com.Team22.preproject.StackOverFlow.question.dto.QuestionResponseDto;
import com.Team22.preproject.StackOverFlow.question.entity.Question;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnswerMapper {


    default Answer createAnswerDtoToAnswer(AnswerRequestDto.CreateAnswerDto createAnswerDto){
        Answer answer = new Answer();
        Member member = new Member();
        member.setMemberId(createAnswerDto.getMemberId());
        answer.setMember(member);
        Question question = new Question();
        question.setQuestionId(createAnswerDto.getQuestionId());
        answer.setQuestion(question);
        answer.setAnswer(createAnswerDto.getAnswer());
        return answer;
    }

    default AnswerResponseDto.AnswerInfo answerToAnswersInfo(Answer answer){
        return AnswerResponseDto.AnswerInfo.builder()
                .answerId(answer.getAnswerId())
                .answer(answer.getAnswer())
                .questionInfo(QuestionResponseDto.QuestionInfo.builder()
                        .questionId(answer.getQuestion().getQuestionId())
                        .title(answer.getQuestion().getTitle())
                        .question(answer.getQuestion().getQuestion())
                        .build())
                .build();
    }
}
