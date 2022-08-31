package com.Team22.preproject.StackOverFlow.answer.mapper;

import com.Team22.preproject.StackOverFlow.answer.dto.AnswerRequestDto;
import com.Team22.preproject.StackOverFlow.answer.dto.AnswerResponseDto;
import com.Team22.preproject.StackOverFlow.answer.entity.Answer;
import com.Team22.preproject.StackOverFlow.member.dto.MemberResponseDto;
import com.Team22.preproject.StackOverFlow.member.entity.Member;
import com.Team22.preproject.StackOverFlow.question.dto.QuestionResponseDto;
import com.Team22.preproject.StackOverFlow.question.entity.Question;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

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

    AnswerResponseDto.CreateAnswerDto createAnswerDtoInfo(Answer answer);


    default AnswerResponseDto.AnswerInfo answerToAnswersInfo(Answer answer){
        return AnswerResponseDto.AnswerInfo.builder()
                .answerId(answer.getAnswerId())
                .answer(answer.getAnswer())
                .questionInfo(QuestionResponseDto.questionAnswerDto.builder()
                        .questionId(answer.getQuestion().getQuestionId())
                        .nickName(answer.getMember().getNickName())
                        .question(answer.getQuestion().getQuestion())
                        .title(answer.getQuestion().getTitle())
                        .build())
                .build();
    }

    default List<AnswerResponseDto.AnswerInfo> answerListToAnswerInfoList(List<Answer> answerList){
        return answerList.stream().map(this::answerToAnswersInfo).collect(Collectors.toList());
    }


    default Answer updateAnswerToAnswer(AnswerRequestDto.UpdateAnswerDto answerDto){
        Question question = new Question();
        Member member = new Member();
        member.setMemberId(answerDto.getMemberId());
        question.setMember(member);
        question.setQuestionId(answerDto.getQuestionId());
        Answer answer = new Answer();
        answer.setAnswerId(answerDto.getAnswerId());
        answer.setQuestion(question);
        answer.setAnswer(answerDto.getAnswer());
        return answer;
    }
}
