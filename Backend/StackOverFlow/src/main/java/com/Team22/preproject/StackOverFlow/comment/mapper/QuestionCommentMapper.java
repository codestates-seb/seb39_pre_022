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
        questionComment.setQuestionComments(createQuestionCommentDto.getQuestionComments());

        return questionComment;
    }

    default QuestionCommentResponseDto.QuestionCommentsInfo questionCommentToCommentInfo(QuestionComment questionComment){
        return QuestionCommentResponseDto.QuestionCommentsInfo.builder()
                .questionCommentsId(questionComment.getQuestionCommentsId())
                .questionComments(questionComment.getQuestionComments())
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
        questionComment.setQuestionCommentsId(updateQuestionCommentDto.getQuestionCommentsId());
        questionComment.setQuestion(question);
        questionComment.setQuestionComments(updateQuestionCommentDto.getQuestionComments());
        return questionComment;
    }

}
