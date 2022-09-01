package com.Team22.preproject.StackOverFlow.comment.mapper;

import com.Team22.preproject.StackOverFlow.comment.dto.QuestionCommentRequestDto.CreateQuestionCommentDto;
import com.Team22.preproject.StackOverFlow.comment.dto.QuestionCommentRequestDto.UpdateQuestionCommentDto;
import com.Team22.preproject.StackOverFlow.comment.dto.QuestionCommentResponseDto;
import com.Team22.preproject.StackOverFlow.comment.entity.QuestionComment;
import com.Team22.preproject.StackOverFlow.member.dto.MemberResponseDto;
import com.Team22.preproject.StackOverFlow.question.entity.Question;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionCommentMapper {

    default QuestionComment createQuestionCommentDtoToComment(CreateQuestionCommentDto createQuestionCommentDto) {
        QuestionComment questionComment = new QuestionComment();

        questionComment.setMember(createQuestionCommentDto.getMember());
        Question question = new Question();
        question.setQuestionId(createQuestionCommentDto.getQuestionId());
        questionComment.setQuestion(question);
        questionComment.setQuestionComment(createQuestionCommentDto.getQuestionComment());
        return questionComment;
    }


    default QuestionComment updateQuestionCommentDtoToQuestionComment(UpdateQuestionCommentDto updateQuestionCommentDto) {
        QuestionComment questionComment = new QuestionComment();

        // QuestionComment 자체 내용
        questionComment.setQuestionComment(updateQuestionCommentDto.getQuestionComment());
        questionComment.setQuestionCommentId(updateQuestionCommentDto.getQuestionCommentId());
        // Question 참조
        Question question = new Question();
        question.setQuestionId(updateQuestionCommentDto.getQuestionId());
        questionComment.setQuestion(question);
        // Member 참조
        questionComment.setMember(updateQuestionCommentDto.getMember());

        return questionComment;
    }

    default QuestionCommentResponseDto.QuestionCommentsInfo questionCommentToCommentInfo(QuestionComment questionComment){
        return QuestionCommentResponseDto.QuestionCommentsInfo.builder()
                .questionCommentId(questionComment.getQuestionCommentId())
                .questionComments(questionComment.getQuestionComment())
                .member(MemberResponseDto.MemberComments.builder()
                        .nickName(questionComment.getMember().getNickName())
                        .build())
                .build();
    }
}
