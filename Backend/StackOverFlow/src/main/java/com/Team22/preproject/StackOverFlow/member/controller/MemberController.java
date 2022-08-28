package com.Team22.preproject.StackOverFlow.member.controller;

import com.Team22.preproject.StackOverFlow.dto.response.MessageResponseDto;
import com.Team22.preproject.StackOverFlow.dto.response.SingleResponseWithMessageDto;
import com.Team22.preproject.StackOverFlow.member.dto.MemberRequestDto;
import com.Team22.preproject.StackOverFlow.member.dto.MemberResponseDto;
import com.Team22.preproject.StackOverFlow.member.entity.Member;
import com.Team22.preproject.StackOverFlow.member.mapper.MemberMapper;
import com.Team22.preproject.StackOverFlow.member.service.MemberService;
import com.Team22.preproject.StackOverFlow.question.entity.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.PresentationDirection;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Positive;

import java.io.IOException;

import static com.Team22.preproject.StackOverFlow.question.entity.SessionConst.*;

@Validated
@Slf4j
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberMapper mapper;
<<<<<<< HEAD
//    private final PasswordEncoder passwordEncoder;
=======
    private final PasswordEncoder passwordEncoder;
    /*
    로그아웃 기능 구현 필요
    - 로그아웃 기능을 구현하려면 securityConfig 파일을 수정해야한다 - 용호님과 의논
     */
>>>>>>> 16e2eeff3451663c8a95b0710e7f6a171cc22333

    //회원가입
    @PostMapping("/signup")
    public ResponseEntity singUp(@RequestBody @Valid MemberRequestDto.singUpDto singUpDto){
//        singUpDto.setPassword(passwordEncoder.encode(singUpDto.getPassword()));
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
<<<<<<< HEAD
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity login(@RequestBody @Valid MemberRequestDto.loginDto loginDto, HttpServletRequest request, HttpServletResponse response) throws IOException {

        Member loginMember = memberService.login(loginDto);
        // 세션 생성
        HttpSession session = request.getSession(true);
        session.setAttribute(LOGIN_MEMBER, loginMember); //   import 로 SessionConst 의 문자열 상수를 사용했습니다.

        // 추후 홈페이지로 이동도록 해야겠습니다. HTTP 요청의 location 부분을 바꾸고 부라우져에게 redirect 요청을 하게 합니다.
//        response.sendRedirect("/");
        log.info("loginMember : {}", loginMember);

        return new ResponseEntity<>(new SingleResponseWithMessageDto<>(mapper.memberToMemberInfo(loginMember),"SUCCESS"),HttpStatus.OK);
=======
    public ResponseEntity login(@RequestBody @Valid MemberRequestDto.loginDto loginDto){
        Member member = mapper.loginDtoToMember(loginDto);
        Member loginMember = memberService.login(member);
//        System.out.println("longinMember = " + loginMember);
//        log.info("loginMember : {}", loginMember);
        return new ResponseEntity<>(new SingleResponseWithMessageDto<>(mapper.memberToMemberInfo(loginMember),"SUCCESS"),HttpStatus.OK);
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
>>>>>>> 16e2eeff3451663c8a95b0710e7f6a171cc22333
    }
}
