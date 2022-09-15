package com.Team22.preproject.StackOverFlow.question.controller;

import com.Team22.preproject.StackOverFlow.dto.response.MessageResponseDto;
import com.Team22.preproject.StackOverFlow.member.entity.Member;
import com.Team22.preproject.StackOverFlow.question.dto.QuestionRequestDto;
import com.Team22.preproject.StackOverFlow.question.dto.QuestionResponseDto;
import com.Team22.preproject.StackOverFlow.question.entity.Question;
import com.Team22.preproject.StackOverFlow.question.entity.SessionConst;
import com.Team22.preproject.StackOverFlow.question.mapper.QuestionMapper;
import com.Team22.preproject.StackOverFlow.question.service.QuestionService;
import com.Team22.preproject.StackOverFlow.stub.member.MemberStub;
import com.Team22.preproject.StackOverFlow.stub.question.QuestionStub;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static com.Team22.preproject.StackOverFlow.question.entity.SessionConst.LOGIN_MEMBER;
import static org.junit.jupiter.api.Assertions.*;
import static com.Team22.preproject.StackOverFlow.util.ApiDocumentUtils.getRequestPreProcessor;
import static com.Team22.preproject.StackOverFlow.util.ApiDocumentUtils.getResponsePreProcessor;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
@WebMvcTest(QuestionController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
@JsonSerialize(using = ToStringSerializer.class)
@AutoConfigureMockMvc(addFilters = false)
class QuestionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    private SessionConst sessionConst;

    @MockBean
    private QuestionService questionService;

    @MockBean
    private QuestionMapper mapper;

    @Test
    @DisplayName("질문 등록 테스트")
    void createQuestion() throws Exception {
        //given
        Member member = MemberStub.getMember();
        Question question = QuestionStub.getQuestion(member);
//        MockHttpSession  session = new MockHttpSession();
//        session.setAttribute(LOGIN_MEMBER,member);
        QuestionRequestDto.CreatedQuestionDto createdQuestionDto = QuestionStub.createdQuestionDto();
        QuestionResponseDto.CreateQuestionDto createResponseDto = QuestionStub.createResponse();
        MessageResponseDto messageResponseDto = QuestionStub.createResult();
        String content = gson.toJson(createdQuestionDto);
        System.out.println("content = " + content);
//        System.out.println("session = " + session);

        given(mapper.createQuestionDtoToQuestion(Mockito.any(QuestionRequestDto.CreatedQuestionDto.class))).willReturn(question);
        given(questionService.createdQuestion(Mockito.any(Question.class))).willReturn(question);
        given(mapper.createdDtoToQuestion(Mockito.any(Question.class))).willReturn(createResponseDto);

        //when
        ResultActions actions = mockMvc.perform(
                post("/questions")
                        .sessionAttr(LOGIN_MEMBER,member)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)

        );

        //then
        actions.andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.title").value(createResponseDto.getTitle()))
                .andExpect(jsonPath("$.message").value(messageResponseDto.getMessage()))
                .andDo(document(
                        "question-create",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestHeaders(headerWithName(LOGIN_MEMBER).description("세션")),
                        requestFields(
                                List.of(
                                        fieldWithPath("title").type(JsonFieldType.STRING).description("제목"),
                                        fieldWithPath("question").type(JsonFieldType.STRING).description("질문 내용"),
                                        fieldWithPath("session").type(JsonFieldType.STRING).description("세션"),
                                        fieldWithPath("member").type(Member.class)
                                        )
                        ), responseFields(
                                List.of(
                                        fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
                                        fieldWithPath("data.questionId").type(JsonFieldType.NUMBER).description("잘문 아이디"),
                                        fieldWithPath("data.title").type(JsonFieldType.STRING).description("제목"),
                                        fieldWithPath("data.question").type(JsonFieldType.STRING).description("질문 내용"),
                                        fieldWithPath("message").type(JsonFieldType.STRING).description("메시지")
                                )
                        )
                ));
    }

    @Test
    void getQuestion() {
    }

    @Test
    void getMyQuestions() {
    }

    @Test
    void updateQuestion() {
    }

    @Test
    void deleteQuestion() {
    }

    @Test
    void getQuestions() {
    }
}