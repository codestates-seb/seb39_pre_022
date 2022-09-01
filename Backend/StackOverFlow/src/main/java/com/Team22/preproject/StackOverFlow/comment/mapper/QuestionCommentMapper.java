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

    default QuestionCommentResponseDto.QuestionCommentsInfo questionCommentToCommentInfo(QuestionComment questionComment){
        return QuestionCommentResponseDto.QuestionCommentsInfo.builder()
                .questionCommentId(questionComment.getQuestionCommentId())
                .questionComments(questionComment.getQuestionComment())
                .member(MemberResponseDto.MemberComments.builder()
                        .nickName(questionComment.getMember().getNickName())
                        .build())
                .build();
    }

    default QuestionComment updateQuestionCommentDtoToComment(UpdateQuestionCommentDto updateQuestionCommentDto){
        QuestionComment questionComment = new QuestionComment();

        questionComment.setMember(updateQuestionCommentDto.getMember());

        Question question = new Question();
        question.setQuestionId(updateQuestionCommentDto.getQuestionId());
        questionComment.setQuestionCommentId(updateQuestionCommentDto.getQuestionCommentId());
        questionComment.setQuestion(question);
        questionComment.setQuestionComment(updateQuestionCommentDto.getQuestionComment());
        return questionComment;
    }

}
