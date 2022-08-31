package com.Team22.preproject.StackOverFlow.member.controller;

import com.Team22.preproject.StackOverFlow.dto.response.MessageResponseDto;
import com.Team22.preproject.StackOverFlow.dto.response.SingleResponseWithMessageDto;
import com.Team22.preproject.StackOverFlow.member.dto.MemberRequestDto;
import com.Team22.preproject.StackOverFlow.member.dto.MemberResponseDto;
import com.Team22.preproject.StackOverFlow.member.entity.Member;
import com.Team22.preproject.StackOverFlow.member.mapper.MemberMapper;
import com.Team22.preproject.StackOverFlow.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Positive;

import static com.Team22.preproject.StackOverFlow.auth.SessionConst.LOGIN_MEMBER;

@Validated
@Slf4j
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberMapper mapper;
    private final PasswordEncoder passwordEncoder;

    /*
    로그아웃 기능 구현 필요
    - 로그아웃 기능을 구현하려면 securityConfig 파일을 수정해야한다 - 용호님과 의논
     */

    //회원가입
    @PostMapping("/signup")
    public ResponseEntity singUp(@RequestBody @Valid MemberRequestDto.singUpDto singUpDto){
        singUpDto.setPassword(passwordEncoder.encode(singUpDto.getPassword()));
        Member member = mapper.signUpDtoToMember(singUpDto);
        memberService.createMember(member);
        System.out.println("member = " + member);

        MessageResponseDto message = MessageResponseDto.builder()
                .message("WELCOME")
                .build();

        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid MemberRequestDto.loginDto loginDto, HttpServletRequest request, HttpServletResponse response){
        Member member = mapper.loginDtoToMember(loginDto);
        Member loginMember = memberService.login(member);

        HttpSession session = request.getSession(true); // sessionId: 12321414 가 쿠키에 등록됌
        session.setAttribute(LOGIN_MEMBER, loginMember);
        return new ResponseEntity<>(new SingleResponseWithMessageDto<>(mapper.memberToMemberInfo(loginMember),"SUCCESS"),HttpStatus.OK);
    }


    @PostMapping("/logout")
    public String logout(HttpServletRequest request)
    {
        HttpSession session = request.getSession(false);

        if (session == null)
        {
            throw new IllegalArgumentException("session이 없는 요청입니다.");
        }

        session.invalidate();

        return "redirect:/";
    }

    //회원 정보 수정
    @PatchMapping("/{member-id}")
    public ResponseEntity updateMember(@Positive @PathVariable("member-id") long memberId, @RequestBody MemberRequestDto.updateDto updateDto){
        updateDto.setMemberId(memberId);
        updateDto.setPassword(passwordEncoder.encode(updateDto.getPassword()));
        Member member = memberService.updateMember(mapper.updateDtoToMember(updateDto));
        MemberResponseDto.UpdateDto memberInfo = mapper.memberToUpdateDto(member);
        return new ResponseEntity<>(new SingleResponseWithMessageDto(memberInfo, "SUCCESS"), HttpStatus.OK);
    }

    //회원정보 삭제
    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(@Positive @PathVariable("member-id") long memberId){
        memberService.deleteMember(memberId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
