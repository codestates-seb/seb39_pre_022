package com.Team22.preproject.StackOverFlow.question.mapper;

import com.Team22.preproject.StackOverFlow.question.dto.QuestionResponseDto;
import com.Team22.preproject.StackOverFlow.question.entity.Question;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-29T23:48:48+0900",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.13 (Oracle Corporation)"
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
        questionInfo.title( question.getTitle() );
        questionInfo.question( question.getQuestion() );

        return questionInfo.build();
    }

    @Override
    public List<QuestionResponseDto.QuestionInfo> questionToQuestionInfo(List<Question> questionList) {
        if ( questionList == null ) {
            return null;
        }

        List<QuestionResponseDto.QuestionInfo> list = new ArrayList<QuestionResponseDto.QuestionInfo>( questionList.size() );
        for ( Question question : questionList ) {
            list.add( questionToInfo( question ) );
        }

        return list;
    }

    @Override
    public List<QuestionResponseDto.QuestionInfoList> questionToQuestionInfoList(List<Question> questionList) {
        if ( questionList == null ) {
            return null;
        }

        List<QuestionResponseDto.QuestionInfoList> list = new ArrayList<QuestionResponseDto.QuestionInfoList>( questionList.size() );
        for ( Question question : questionList ) {
            list.add( questionToInfoList( question ) );
        }

        return list;
    }

    @Override
    public List<QuestionResponseDto.QuestionAndAnswer> questionToQuestionAnswerInfoList(List<Question> questionList) {
        if ( questionList == null ) {
            return null;
        }

        List<QuestionResponseDto.QuestionAndAnswer> list = new ArrayList<QuestionResponseDto.QuestionAndAnswer>( questionList.size() );
        for ( Question question : questionList ) {
            list.add( questionToQuestionAndAnswer( question ) );
        }

        return list;
    }

    protected QuestionResponseDto.QuestionAndAnswer questionToQuestionAndAnswer(Question question) {
        if ( question == null ) {
            return null;
        }

        QuestionResponseDto.QuestionAndAnswer.QuestionAndAnswerBuilder questionAndAnswer = QuestionResponseDto.QuestionAndAnswer.builder();

        questionAndAnswer.questionId( question.getQuestionId() );
        questionAndAnswer.title( question.getTitle() );
        questionAndAnswer.question( question.getQuestion() );

        return questionAndAnswer.build();
    }
}