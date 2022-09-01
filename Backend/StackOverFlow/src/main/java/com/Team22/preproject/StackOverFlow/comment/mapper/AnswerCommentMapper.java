package com.Team22.preproject.StackOverFlow.comment.mapper;

import com.Team22.preproject.StackOverFlow.answer.entity.Answer;
import com.Team22.preproject.StackOverFlow.comment.dto.AnswerCommentRequestDto.createAnswerCommentDto;
import com.Team22.preproject.StackOverFlow.comment.dto.AnswerCommentRequestDto.updateAnswerCommentDto;
import com.Team22.preproject.StackOverFlow.comment.dto.AnswerCommentResponseDto.AnswerCommentInfo;
import com.Team22.preproject.StackOverFlow.comment.entity.AnswerComment;
import com.Team22.preproject.StackOverFlow.member.dto.MemberResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnswerCommentMapper {

    public default AnswerComment createAnswerCommentDtoToAnswerComment(createAnswerCommentDto createAnswerCommentDto)
    {
        AnswerComment answerComment = new AnswerComment();
        Answer answer = new Answer();

        answerComment.setAnswerComments(createAnswerCommentDto.getAnswerComments());
        answerComment.setMember(createAnswerCommentDto.getMember());

        answer.setAnswerId(createAnswerCommentDto.getAnswerId());
        answerComment.setAnswer(answer);

        return answerComment;
    }

    public default AnswerComment updateAnswerCommentDtoToAnswerComment(updateAnswerCommentDto updateAnswerCommentDto)
    {
        AnswerComment answerComment = new AnswerComment();
        Answer answer = new Answer();

        answerComment.setAnswerCommentsId(updateAnswerCommentDto.getAnswerCommentsId());
        answerComment.setAnswerComments(updateAnswerCommentDto.getAnswerComments());
        answerComment.setMember(updateAnswerCommentDto.getMember());

        answer.setAnswerId(updateAnswerCommentDto.getAnswerId());
        answerComment.setAnswer(answer);

        return answerComment;
    }


    public default AnswerCommentInfo answerCommentToAnswerCommentInfo(AnswerComment answerComment)
    {
        AnswerCommentInfo answerCommentInfo = new AnswerCommentInfo();
        answerCommentInfo.setAnswerCommentsId(answerComment.getAnswerCommentsId());
        answerCommentInfo.setAnswerComments(answerComment.getAnswerComments());
        answerCommentInfo.setMember(MemberResponseDto.MemberComments.builder()
                .nickName(answerComment.getMember().getNickName()).build());

        return answerCommentInfo;
    }

}
