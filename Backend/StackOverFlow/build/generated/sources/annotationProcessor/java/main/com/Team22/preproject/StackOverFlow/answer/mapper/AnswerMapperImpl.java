package com.Team22.preproject.StackOverFlow.answer.mapper;

import com.Team22.preproject.StackOverFlow.answer.dto.AnswerResponseDto;
import com.Team22.preproject.StackOverFlow.answer.entity.Answer;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-29T17:44:54+0900",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.jar, environment: Java 11.0.13 (Oracle Corporation)"
)
@Component
public class AnswerMapperImpl implements AnswerMapper {

    @Override
    public AnswerResponseDto.CreateAnswerDto createAnswerDtoInfo(Answer answer) {
        if ( answer == null ) {
            return null;
        }

        AnswerResponseDto.CreateAnswerDto.CreateAnswerDtoBuilder createAnswerDto = AnswerResponseDto.CreateAnswerDto.builder();

        createAnswerDto.answerId( answer.getAnswerId() );
        createAnswerDto.answer( answer.getAnswer() );

        return createAnswerDto.build();
    }
}