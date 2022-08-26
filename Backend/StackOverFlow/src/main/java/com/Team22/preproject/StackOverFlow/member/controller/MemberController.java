package com.Team22.preproject.StackOverFlow.member.controller;

import com.Team22.preproject.StackOverFlow.dto.response.MessageResponseDto;
import com.Team22.preproject.StackOverFlow.dto.response.SingleResponseWithMessageDto;
import com.Team22.preproject.StackOverFlow.member.dto.MemberRequestDto;
import com.Team22.preproject.StackOverFlow.member.entity.Member;
import com.Team22.preproject.StackOverFlow.member.mapper.MemberMapper;
import com.Team22.preproject.StackOverFlow.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
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

        MessageResponseDto message = MessageResponseDto.builder()
                .message("WELCOME")
                .build();

        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid MemberRequestDto.loginDto loginDto){
        Member member = mapper.loginDtoToMember(loginDto);
        memberService.login(member);
        return new ResponseEntity<>(new SingleResponseWithMessageDto<>(mapper.memberToMemberInfo(member),"SUCCESS"),HttpStatus.OK);
//        return new ResponseEntity<>(new SingleResponseWithMessageDto<>(member1,"SUCCESS"),HttpStatus.OK);
    }
}
