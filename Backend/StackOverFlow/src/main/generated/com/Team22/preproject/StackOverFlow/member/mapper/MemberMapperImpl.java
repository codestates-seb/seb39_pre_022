package com.Team22.preproject.StackOverFlow.member.mapper;

import com.Team22.preproject.StackOverFlow.member.dto.MemberRequestDto;
import com.Team22.preproject.StackOverFlow.member.entity.Member;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-26T11:21:02+0900",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.13 (Oracle Corporation)"
)
@Component
public class MemberMapperImpl implements MemberMapper {

    @Override
    public Member signUpDtoToMember(MemberRequestDto.singUpDto singUpDto) {
        if ( singUpDto == null ) {
            return null;
        }

        Member member = new Member();

        member.setEmail( singUpDto.getEmail() );
        member.setPassword( singUpDto.getPassword() );
        member.setNickName( singUpDto.getNickName() );

        return member;
    }

    @Override
    public Member loginDtoToMember(MemberRequestDto.loginDto loginDto) {
        if ( loginDto == null ) {
            return null;
        }

        Member member = new Member();

        member.setEmail( loginDto.getEmail() );
        member.setPassword( loginDto.getPassword() );

        return member;
    }
}
