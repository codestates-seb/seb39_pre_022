package com.Team22.preproject.StackOverFlow.question.controller;

import com.Team22.preproject.StackOverFlow.dto.response.MessageResponseDto;
import com.Team22.preproject.StackOverFlow.question.dto.QuestionRequestDto;
import com.Team22.preproject.StackOverFlow.question.dto.QuestionResponseDto;
import com.Team22.preproject.StackOverFlow.question.entity.Question;
import com.Team22.preproject.StackOverFlow.question.mapper.QuestionMapper;
import com.Team22.preproject.StackOverFlow.question.service.QuestionService;
import com.Team22.preproject.StackOverFlow.stub.question.QuestionStub;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static com.Team22.preproject.StackOverFlow.util.ApiDocumentUtils.getRequestPreProcessor;
import static com.Team22.preproject.StackOverFlow.util.ApiDocumentUtils.getResponsePreProcessor;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
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
@AutoConfigureMockMvc(addFilters = false)
class QuestionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    private QuestionService questionService;

    @MockBean
    private QuestionMapper mapper;

    @Test
    @DisplayName("질문 등록 테스트")
    void createQuestion() {
        //given
        Question question = QuestionStub.getQuestion();
        QuestionRequestDto.CreatedQuestionDto createdQuestionDto = QuestionStub.createdQuestionDto();
        QuestionResponseDto.CreateQuestionDto createResponseDto = QuestionStub.createResponse();
        MessageResponseDto messageResponseDto = QuestionStub.createResult();
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