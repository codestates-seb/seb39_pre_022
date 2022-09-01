package com.Team22.preproject.StackOverFlow.member.controller;

import com.Team22.preproject.StackOverFlow.dto.response.MessageResponseDto;
import com.Team22.preproject.StackOverFlow.member.dto.MemberRequestDto;
import com.Team22.preproject.StackOverFlow.member.dto.MemberResponseDto;
import com.Team22.preproject.StackOverFlow.member.entity.Member;
import com.Team22.preproject.StackOverFlow.member.mapper.MemberMapper;
import com.Team22.preproject.StackOverFlow.member.service.MemberService;
import com.Team22.preproject.StackOverFlow.stub.member.MemberStub;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.servlet.http.HttpSession;
import java.util.List;

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
@WebMvcTest(MemberController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
@AutoConfigureMockMvc(addFilters = false)
class MemberControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson gson;

    @MockBean
    private MemberService memberService;

    @MockBean
    private MemberMapper mapper;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("회원가입")
    void singUp() throws Exception {
        //given
        Member member = MemberStub.getMember();
        MemberRequestDto.singUpDto singUpDto = MemberStub.singUpDto();
        MessageResponseDto messageResponseDto = MemberStub.signUpResult();
        String content = gson.toJson(singUpDto);

        given(mapper.signUpDtoToMember(Mockito.any(MemberRequestDto.singUpDto.class))).willReturn(member);
        given(memberService.createMember(Mockito.any(Member.class))).willReturn(member);

        //when
        ResultActions actions = mockMvc.perform(
                post("/members/signup")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)

        );
        //then
        actions.andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value(messageResponseDto.getMessage()))
                .andDo(document(
                        "member-create",
                        getRequestPreProcessor(),
                        getResponsePreProcessor(),
                        requestFields(
                                List.of(
                                        fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
                                        fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호"),
                                        fieldWithPath("nickName").type(JsonFieldType.STRING).description("닉네임")
                                )
                        ),responseFields(
                                List.of(
                                        fieldWithPath("message").type(JsonFieldType.STRING).description("메세지")
                                )
                        )

                ));
    }

    @Test
    @DisplayName("로그인")
    void login() throws Exception {
        //given
        MemberRequestDto.loginDto loginDto = MemberStub.loginDto();
        Member member = MemberStub.getMember();
        MemberResponseDto.MemberInfo memberInfo = MemberStub.memberInfo(member);
        HttpSession httpSession = new MockHttpSession();
        String content = gson.toJson(loginDto);

        given(mapper.loginDtoToMember(Mockito.any(MemberRequestDto.loginDto.class))).willReturn(member);
        given(memberService.login(Mockito.any(Member.class))).willReturn(member);
        given(mapper.memberToMemberInfo(Mockito.any(Member.class))).willReturn(memberInfo);

        //when
        ResultActions actions = mockMvc.perform(
                post("/members/login")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        );
        System.out.println("content = " + content);

        //then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.data.email").value(member.getEmail()))
                .andExpect(jsonPath("$.data.nickName").value(member.getNickName()))
                .andExpect(jsonPath("$.message").value("SUCCESS"))
                .andDo(
                        document("member-login",
                                getRequestPreProcessor(),
                                getResponsePreProcessor(),
                                requestFields(
                                        List.of(
                                                fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
                                                fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호")
                                        )
                                ),
                                responseFields(
                                        List.of(
                                                fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
                                                fieldWithPath("data.email").type(JsonFieldType.STRING).description("이메일"),
                                                fieldWithPath("data.nickName").type(JsonFieldType.STRING).description("닉네임"),
                                                fieldWithPath("message").type(JsonFieldType.STRING).description("메시지")
                                        )
                                ))
                );
    }

    @Test
    @DisplayName("회원정보 수정")
    void updateMember() throws Exception {
        //given
        Member member = MemberStub.getMember();
        MemberRequestDto.updateDto updateDto = MemberStub.updateRequestDto();
        MemberResponseDto.UpdateDto updateResponseDto = MemberStub.updateResponseDto();

        String content = gson.toJson(updateDto);

        given(mapper.updateDtoToMember(Mockito.any(MemberRequestDto.updateDto.class))).willReturn(member);
        given(memberService.updateMember(Mockito.any(Member.class))).willReturn(member);
        given(mapper.memberToUpdateDto(Mockito.any(Member.class))).willReturn(updateResponseDto);

        //when
        ResultActions actions = mockMvc.perform(
                patch("/members/{member-id}",member.getMemberId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        );

        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$.data.nickName").value(updateResponseDto.getNickName()))
                .andExpect(jsonPath("$.message").value("SUCCESS"))
                .andDo(
                        document("member-update",
                                getRequestPreProcessor(),
                                getResponsePreProcessor(),
                                requestFields(
                                        List.of(
                                                fieldWithPath("memberId").type(JsonFieldType.NUMBER).description("회원 식별자").ignored(),
                                                fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호").optional(),
                                                fieldWithPath("nickName").type(JsonFieldType.STRING).description("닉네임").optional()
                                        )
                                ),
                                responseFields(
                                        List.of(
                                                fieldWithPath("data").type(JsonFieldType.OBJECT).description("결과 데이터"),
                                                fieldWithPath("data.nickName").type(JsonFieldType.STRING).description("닉네임"),
                                                fieldWithPath("data.memberId").type(JsonFieldType.NUMBER).description("회원 식별자"),
                                                fieldWithPath("message").type(JsonFieldType.STRING).description("결과 메시지")
                                        )
                                )
                        )
                );

    }

    @Test
    @DisplayName("회원 삭제")
    void deleteMember() throws Exception {
        //given
        Member member = MemberStub.getMember();
        doNothing().when(memberService).deleteMember(member.getMemberId());

        ResultActions actions = mockMvc.perform(
                delete("/members/{member-id}",member.getMemberId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        actions
                .andExpect(status().isNoContent())
                .andDo(
                        document("member-delete",
                                getRequestPreProcessor(),
                                getResponsePreProcessor()
                        )
                );

    }
}