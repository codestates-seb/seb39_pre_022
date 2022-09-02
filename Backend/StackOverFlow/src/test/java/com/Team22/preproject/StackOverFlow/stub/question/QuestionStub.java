package com.Team22.preproject.StackOverFlow.stub.question;

import com.Team22.preproject.StackOverFlow.dto.response.MessageResponseDto;
import com.Team22.preproject.StackOverFlow.member.entity.Member;
import com.Team22.preproject.StackOverFlow.question.dto.QuestionRequestDto;
import com.Team22.preproject.StackOverFlow.question.dto.QuestionResponseDto;
import com.Team22.preproject.StackOverFlow.question.entity.Question;
import com.Team22.preproject.StackOverFlow.stub.member.MemberStub;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public static Page<Question> getQuestionPage(int page, int size){
        return new PageImpl<>(List.of(
                getQuestions("java", "java is ...", 1L),
                getQuestions("python", "python is ...", 2L),
                getQuestions("JS", "Js is ...", 3L)
        ), PageRequest.of(page-1,size, Sort.by("QUESTION_ID").descending()),3);
    }

    public static List<QuestionResponseDto.QuestionInfo> questionInfoList(int page, int size){
        return getQuestionPage(page, size).stream().map(question -> {
            return QuestionResponseDto.QuestionInfo.builder()
                    .title(question.getTitle())
                    .question(question.getQuestion())
                    .build();
        }).collect(Collectors.toList());
    }

    public static QuestionResponseDto.QuestionInfo questionInfo(){
        return QuestionResponseDto.QuestionInfo.builder()
                .title(getQuestion().getTitle())
                .question(getQuestion().getQuestion())
                .build();
    }


    public static QuestionRequestDto.UpdateQuestionDto updateQuestionDto(Question question){
        return QuestionRequestDto.UpdateQuestionDto.builder()
                .questionId(1L)
                .member(member)
                .title("title change")
                .question("change")
                .build();
    }

    public static QuestionResponseDto.UpdateQuestionDto updateQuestionInfo(Question question){
        QuestionRequestDto.UpdateQuestionDto updateQuestionDto = updateQuestionDto(question);
        return QuestionResponseDto.UpdateQuestionDto.builder()
                .questionId(updateQuestionDto.getQuestionId())
                .title(updateQuestionDto.getTitle())
                .question(updateQuestionDto.getQuestion())
                .build();
    }


    public static MessageResponseDto createResult() {
        return MessageResponseDto.builder()
                .message("CREATED")
                .build();
    }
}
