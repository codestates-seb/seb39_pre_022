package com.Team22.preproject.StackOverFlow.question.mapper;

import com.Team22.preproject.StackOverFlow.member.entity.Member;
import com.Team22.preproject.StackOverFlow.question.dto.QuestionRequestDto;
import com.Team22.preproject.StackOverFlow.question.dto.QuestionResponseDto;
import com.Team22.preproject.StackOverFlow.question.entity.Question;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuestionMapper{
//    Question createQuestionDtoToQuestion(QuestionRequestDto.CreatedQuestionDto createdQuestionDto);
    default Question createQuestionDtoToQuestion(QuestionRequestDto.CreatedQuestionDto createdQuestionDto){
        Question question = new Question();
        Member member = new Member();
        member.setMemberId(createdQuestionDto.getMemberId());
        question.setMember(member);
        question.setTitle(createdQuestionDto.getTitle());
        question.setQuestion(createdQuestionDto.getQuestion());
        return question;
    }

    QuestionResponseDto.CreateQuestionDto createdDtoToQuestion(Question question);

    QuestionResponseDto.QuestionInfo questionToInfo(Question question);
    List<QuestionResponseDto.QuestionInfo> questionToQuestionInfo(List<Question> questionList);

    default QuestionResponseDto.QuestionInfoList questionToInfoList(Question question){
        return QuestionResponseDto.QuestionInfoList.builder()
                .questionId(question.getQuestionId())
                .title(question.getTitle())
                .question(question.getQuestion())
                .memberId(question.getMember().getMemberId())
                .build();
    }

    List<QuestionResponseDto.QuestionInfoList> questionToQuestionInfoList(List<Question> questionList);

    default Question updateQuestionDtoToQuestion(QuestionRequestDto.UpdateQuestionDto updateQuestionDto){
        Question question = new Question();
        Member member = new Member();
        member.setMemberId(updateQuestionDto.getMemberId());
        question.setMember(member);
        question.setQuestionId(updateQuestionDto.getQuestionId());
        question.setTitle(updateQuestionDto.getTitle());
        question.setQuestion(updateQuestionDto.getQuestion());
        return question;
    }

//    List<QuestionResponseDto.QuestionAndAnswer> questionToQuestionAnswerInfoList(List<Question> questionList);

}
