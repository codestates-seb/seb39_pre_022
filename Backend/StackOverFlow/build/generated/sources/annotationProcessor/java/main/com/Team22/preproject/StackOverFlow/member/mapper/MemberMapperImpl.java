package com.Team22.preproject.StackOverFlow.member.mapper;

import com.Team22.preproject.StackOverFlow.member.dto.MemberRequestDto;
import com.Team22.preproject.StackOverFlow.member.entity.Member;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-05T16:25:40+0900",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.jar, environment: Java 11.0.15 (Azul Systems, Inc.)"
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
