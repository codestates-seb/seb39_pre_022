package com.Team22.preproject.StackOverFlow.question.mapper;

import com.Team22.preproject.StackOverFlow.member.entity.Member;
import com.Team22.preproject.StackOverFlow.question.dto.QuestionRequestDto;
import com.Team22.preproject.StackOverFlow.question.dto.QuestionResponseDto;
import com.Team22.preproject.StackOverFlow.question.entity.Question;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionMapper{
    Question createQuestionDtoToQuestion(QuestionRequestDto.CreatedQuestionDto createdQuestionDto);

    QuestionResponseDto.CreateQuestionDto createdDtoToQuestion(Question question);

//    QuestionResponseDto.QuestionInfo questionToInfo(Question question);
    default QuestionResponseDto.QuestionInfo questionToInfo(Question question){
        return QuestionResponseDto.QuestionInfo.builder()
                .questionId(question.getQuestionId())
                .title(question.getTitle())
                .question(question.getQuestion())
                .build();
    }
}
