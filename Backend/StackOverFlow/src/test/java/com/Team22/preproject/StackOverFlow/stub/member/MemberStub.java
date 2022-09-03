package com.Team22.preproject.StackOverFlow.stub.member;


import com.Team22.preproject.StackOverFlow.dto.response.MessageResponseDto;
import com.Team22.preproject.StackOverFlow.member.dto.MemberRequestDto;
import com.Team22.preproject.StackOverFlow.member.dto.MemberResponseDto;
import com.Team22.preproject.StackOverFlow.member.entity.Member;
import com.Team22.preproject.StackOverFlow.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class MemberStub {

    private static MemberMapper mapper;
    private static PasswordEncoder passwordEncoder;


    public static Member getMember(){
        Member member = new Member();
        member.setMemberId(1L);
        member.setEmail("hgd@gmail.com");
        member.setPassword("1234567891");
        member.setNickName("hgd");
        return member;
    }

    //회원가입
    public static MemberRequestDto.singUpDto singUpDto(){
        return MemberRequestDto.singUpDto.builder()
                .email("hgd@gmail.com")
                .nickName("hgd")
                .password("1234567891")
                .build();
    }

    //회원가입 결과
    public static MessageResponseDto signUpResult() {
        return  MessageResponseDto.builder()
                .message("WELCOME")
                .build();
    }

    //로그인 요청
    public static MemberRequestDto.loginDto loginDto(){
        return MemberRequestDto.loginDto.builder()
                .email("hgd@gmail.com")
                .password("1234")
                .build();
    }

    //로그인 응답
    public static MemberResponseDto.MemberInfo memberInfo(Member member){
        return MemberResponseDto.MemberInfo.builder()
                .email(member.getEmail())
                .nickName(member.getNickName())
                .build();
    }

    //회원정보 수정
    public static MemberRequestDto.updateDto updateRequestDto(){
        return MemberRequestDto.updateDto.builder()
                .memberId(getMember().getMemberId())
                .nickName("change")
                .password("5678")
                .build();
    }

    public static MemberResponseDto.UpdateDto updateResponseDto(){
        return MemberResponseDto.UpdateDto.builder()
                .memberId(getMember().getMemberId())
                .nickName("change")
                .build();
    }
}
