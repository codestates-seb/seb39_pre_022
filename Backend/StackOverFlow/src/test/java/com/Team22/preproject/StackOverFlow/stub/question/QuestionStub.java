package com.Team22.preproject.StackOverFlow.stub.question;

import com.Team22.preproject.StackOverFlow.member.entity.Member;
import com.Team22.preproject.StackOverFlow.question.dto.QuestionRequestDto;
import com.Team22.preproject.StackOverFlow.question.dto.QuestionResponseDto;
import com.Team22.preproject.StackOverFlow.question.entity.Question;
import com.Team22.preproject.StackOverFlow.stub.member.MemberStub;

import java.util.ArrayList;

public class QuestionStub {
    private static final Member member = MemberStub.getMember();


    public static Question getQuestions(String title, String question, long questionId){
        Question questions = new Question();
        questions.setQuestionId(questionId);
        questions.setMember(member);
        questions.setQuestion(question);
        questions.setTitle(title);
        questions.setQuestionCommentsList(new ArrayList<>());
        questions.setAnswerList(new ArrayList<>());
        return questions;
    }

    public static Question getQuestion(){
        Question question = new Question();
        question.setQuestionId(question.getQuestionId());
        question.setTitle("How to Java...");
        question.setQuestion("Java is ...");
        question.setMember(member);
        question.setQuestionCommentsList(new ArrayList<>());
        question.setAnswerList(new ArrayList<>());
        return question;
    }

    public static QuestionRequestDto.CreatedQuestionDto createdQuestionDto(){
        return QuestionRequestDto.CreatedQuestionDto.builder()
                .member(member)
                .title("How to Java...")
                .question("Java is ...")
                .build();
    }

    public static QuestionResponseDto.CreateQuestionDto createResponse(){
        return QuestionResponseDto.CreateQuestionDto.builder()
                .questionId(getQuestion().getQuestionId())
                .title(createdQuestionDto().getTitle())
                .question(createdQuestionDto().getQuestion())
                .build();
    }


}
