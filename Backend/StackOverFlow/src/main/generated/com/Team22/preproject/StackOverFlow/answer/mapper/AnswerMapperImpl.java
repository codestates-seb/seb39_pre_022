package com.Team22.preproject.StackOverFlow.answer.mapper;

import com.Team22.preproject.StackOverFlow.answer.dto.AnswerResponseDto;
import com.Team22.preproject.StackOverFlow.answer.entity.Answer;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-01T17:40:20+0900",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.15 (Azul Systems, Inc.)"
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
