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

    default QuestionComment createQuestionCommentDtoToComment(CreateQuestionCommentDto createQCommentDto) {
        QuestionComment questionComment = new QuestionComment();
        questionComment.setMember(createQCommentDto.getMember());

        Question question = new Question();
        question.setQuestionId(createQCommentDto.getQuestionId());

        questionComment.setQuestion(question);
        questionComment.setContents(createQCommentDto.getQuestionComment());

        return questionComment;
    }

    default QuestionCommentResponseDto.QuestionCommentsInfo questionCommentToCommentInfo(QuestionComment questionComment){
        return QuestionCommentResponseDto.QuestionCommentsInfo.builder()
                .questionCommentId(questionComment.getQuestionCommentId())
                .questionComments(questionComment.getContents())
                .member(MemberResponseDto.MemberComments.builder()
                        .nickName(questionComment.getMember().getNickName())
                        .build())
                .build();
    }

    default QuestionComment updateQuestionCommentDtoToComment(UpdateQuestionCommentDto updateQCommentDto){
        QuestionComment questionComment = new QuestionComment();

        questionComment.setMember(updateQCommentDto.getMember());

        Question question = new Question();
        question.setQuestionId(updateQCommentDto.getQuestionId());
        questionComment.setQuestionCommentId(updateQCommentDto.getQuestionCommentId());
        questionComment.setQuestion(question);
        questionComment.setContents(updateQCommentDto.getQuestionComment());
        return questionComment;
    }

}
