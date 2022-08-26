package com.Team22.preproject.StackOverFlow.question.mapper;

import com.Team22.preproject.StackOverFlow.question.dto.QuestionRequestDto;
import com.Team22.preproject.StackOverFlow.question.dto.QuestionResponseDto;
import com.Team22.preproject.StackOverFlow.question.entity.Question;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-27T00:14:45+0900",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.13 (Oracle Corporation)"
)
@Component
public class QuestionMapperImpl implements QuestionMapper {

    @Override
    public Question createQuestionDtoToQuestion(QuestionRequestDto.CreatedQuestionDto createdQuestionDto) {
        if ( createdQuestionDto == null ) {
            return null;
        }

        Question question = new Question();

        question.setQuestion( createdQuestionDto.getQuestion() );
        question.setTitle( createdQuestionDto.getTitle() );

        return question;
    }

    @Override
    public QuestionResponseDto.CreateQuestionDto createdDtoToQuestion(Question question) {
        if ( question == null ) {
            return null;
        }

        QuestionResponseDto.CreateQuestionDto.CreateQuestionDtoBuilder createQuestionDto = QuestionResponseDto.CreateQuestionDto.builder();

        createQuestionDto.questionId( question.getQuestionId() );
        createQuestionDto.question( question.getQuestion() );
        createQuestionDto.title( question.getTitle() );

        return createQuestionDto.build();
    }
}
