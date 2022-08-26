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

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@Validated
@Slf4j
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberMapper mapper;
    private final PasswordEncoder passwordEncoder;

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

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid MemberRequestDto.loginDto loginDto){
        Member member = mapper.loginDtoToMember(loginDto);
        Member loginMember = memberService.login(member);
//        System.out.println("longinMember = " + loginMember);
//        log.info("loginMember : {}", loginMember);
        return new ResponseEntity<>(new SingleResponseWithMessageDto<>(mapper.memberToMemberInfo(loginMember),"SUCCESS"),HttpStatus.OK);
    }

    @PatchMapping("/{member-id}")
    public ResponseEntity updateMember(@Positive @PathVariable("member-id") long memberId, @RequestBody MemberRequestDto.updateDto updateDto){
        updateDto.setMemberId(memberId);
        updateDto.setPassword(passwordEncoder.encode(updateDto.getPassword()));
        Member member = memberService.updateMember(mapper.updateDtoToMember(updateDto));
        MemberResponseDto.UpdateDto memberInfo = mapper.memberToUpdateDto(member);
        return new ResponseEntity<>(new SingleResponseWithMessageDto(memberInfo, "SUCCESS"), HttpStatus.OK);
    }

    @DeleteMapping("/{member-id")
    public ResponseEntity deleteMember(@Positive @PathVariable("member-id") long memberId){
        memberService.deleteMember(memberId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
