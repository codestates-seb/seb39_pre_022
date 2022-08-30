package com.Team22.preproject.StackOverFlow.session;

import com.Team22.preproject.StackOverFlow.member.dto.MemberRequestDto;
import com.Team22.preproject.StackOverFlow.member.entity.Member;
import com.Team22.preproject.StackOverFlow.member.mapper.MemberMapper;
import com.Team22.preproject.StackOverFlow.member.repository.MemberRepository;
import com.Team22.preproject.StackOverFlow.question.entity.SessionConst;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static com.Team22.preproject.StackOverFlow.question.entity.SessionConst.LOGIN_MEMBER;

//@SpringBootTest 통합테스트용 애너테이션
public class SessionTest {

    private MemberMapper mapper;


    @Test
    public void LoginSessionTest(){

//        MockHttpServletResponse response = new MockHttpServletResponse();
//
//        // 가짜 HttpRequest 객체를 생성합니다. given 단계
//        MockHttpServletRequest request = new MockHttpServletRequest();
//
//        MemberRequestDto.singUpDto signMember = MemberRequestDto.singUpDto
//                .builder()
//                .email("stack@gmail.com")
//                .nickName("King stack")
//                .password("1234").build();
//
//        Member member = mapper.signUpDtoToMember(signMember);
//        request.getSession();


    }
}
