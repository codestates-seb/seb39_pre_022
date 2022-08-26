package com.Team22.preproject.StackOverFlow.member.service;

import com.Team22.preproject.StackOverFlow.exception.BusinessLogicException;
import com.Team22.preproject.StackOverFlow.exception.ExceptionCode;
import com.Team22.preproject.StackOverFlow.member.dto.MemberRequestDto;
import com.Team22.preproject.StackOverFlow.member.entity.Member;
import com.Team22.preproject.StackOverFlow.member.mapper.MemberMapper;
import com.Team22.preproject.StackOverFlow.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MemberService {
    private final MemberRepository memberRepository;
//    private final PasswordEncoder passwordEncoder;
    private final MemberMapper mapper;


    public void createMember(Member member) {
        verifyEmail(member.getEmail());
        memberRepository.save(member);
    }

//    public Member login(Member member){
//        Member findMember = findVerifiedMemberByEmail(member.getEmail());
//
//        if(!passwordEncoder.matches(member.getPassword(), findMember.getPassword())) {
//            throw new BusinessLogicException(ExceptionCode.PASSWORD_INCORRECT);
//        }
//
//        return member;
//    }

    public Member login(MemberRequestDto.loginDto loginDto){

        Member member = mapper.loginDtoToMember(loginDto);
        Member findMember = findVerifiedMemberByEmail(member.getEmail());
        log.info("[memberService] findMember : [{}]",findMember );

//        if(!passwordEncoder.matches(member.getPassword(), findMember.getPassword())) {
//            log.error("[MEMBER_SERVICE] Incorrect Password");
//            throw new BusinessLogicException(ExceptionCode.PASSWORD_INCORRECT);
//        }

        if(!member.getPassword().equals(findMember.getPassword())){

            throw new BusinessLogicException(ExceptionCode.PASSWORD_INCORRECT);
        }
        log.info("findMember {}", findMember);
        return findMember;
    }


    public void verifyEmail(String email){
        Optional<Member> member = memberRepository.findByEmail(email);

        if(member.isPresent()){
            throw new BusinessLogicException(ExceptionCode.MEMBER_ALREADY_EXISTS);
        }
    }


    @Transactional(readOnly = true)
    public Member findVerifiedMemberByEmail(String email) {
        Optional<Member> optionalMember = memberRepository.findByEmail(email);
        log.info("service 단의 member {}", optionalMember.get());
        return optionalMember.orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
    }

}
