package com.Team22.preproject.StackOverFlow.question.mapper;

import com.Team22.preproject.StackOverFlow.question.dto.QuestionResponseDto;
import com.Team22.preproject.StackOverFlow.question.entity.Question;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-27T03:13:24+0900",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.jar, environment: Java 11.0.13 (Oracle Corporation)"
)
@Component
public class QuestionMapperImpl implements QuestionMapper {

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

    @Override
    public QuestionResponseDto.QuestionInfo questionToInfo(Question question) {
        if ( question == null ) {
            return null;
        }

        QuestionResponseDto.QuestionInfo.QuestionInfoBuilder questionInfo = QuestionResponseDto.QuestionInfo.builder();

        questionInfo.questionId( question.getQuestionId() );
        questionInfo.question( question.getQuestion() );
        questionInfo.title( question.getTitle() );

        return questionInfo.build();
    }
}
