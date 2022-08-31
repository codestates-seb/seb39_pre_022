package com.Team22.preproject.StackOverFlow.comment.mapper;

import com.Team22.preproject.StackOverFlow.comment.dto.QuestionCommentRequestDto;
import com.Team22.preproject.StackOverFlow.comment.dto.QuestionCommentResponseDto;
import com.Team22.preproject.StackOverFlow.comment.entity.QuestionComment;
import com.Team22.preproject.StackOverFlow.member.dto.MemberResponseDto;
import com.Team22.preproject.StackOverFlow.member.entity.Member;
import com.Team22.preproject.StackOverFlow.question.dto.QuestionResponseDto;
import com.Team22.preproject.StackOverFlow.question.entity.Question;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionCommentMapper {

    default QuestionComment createQuestionCommentDtoToComment(QuestionCommentRequestDto.CreateQCommentDto createQCommentDto) {
        QuestionComment questionComment = new QuestionComment();
        Member member = new Member();
        member.setMemberId(createQCommentDto.getMemberId());
        questionComment.setMember(member);
        Question question = new Question();
        question.setQuestionId(createQCommentDto.getQuestionId());
        questionComment.setQuestion(question);
        questionComment.setQuestionComments(createQCommentDto.getQuestionComments());
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

    default QuestionComment updateQuestionCommentDtoToComment(QuestionCommentRequestDto.UpdateQCommentDto updateQCommentDto){
        QuestionComment questionComment = new QuestionComment();
        Member member = new Member();
        member.setMemberId(updateQCommentDto.getMemberId());
        questionComment.setMember(member);
        Question question = new Question();
        question.setQuestionId(updateQCommentDto.getQuestionId());
        questionComment.setQuestionCommentsId(updateQCommentDto.getQuestionCommentsId());
        questionComment.setQuestion(question);
        questionComment.setQuestionComments(updateQCommentDto.getQuestionComments());
        return questionComment;
    }

}
