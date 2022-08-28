package com.Team22.preproject.StackOverFlow.member.mapper;

import com.Team22.preproject.StackOverFlow.member.dto.MemberRequestDto;
import com.Team22.preproject.StackOverFlow.member.dto.MemberResponseDto;
import com.Team22.preproject.StackOverFlow.member.entity.Member;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
<<<<<<< HEAD
    date = "2022-08-26T20:02:46+0900",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.15 (Azul Systems, Inc.)"
=======
    date = "2022-08-27T03:14:08+0900",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 11.0.13 (Oracle Corporation)"
>>>>>>> 16e2eeff3451663c8a95b0710e7f6a171cc22333
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

<<<<<<< HEAD
=======
        return member;
    }

    @Override
    public MemberResponseDto.MemberInfo memberToMemberInfo(Member member) {
        if ( member == null ) {
            return null;
        }

        MemberResponseDto.MemberInfo.MemberInfoBuilder memberInfo = MemberResponseDto.MemberInfo.builder();

        memberInfo.email( member.getEmail() );
        memberInfo.nickName( member.getNickName() );

        return memberInfo.build();
    }

    @Override
    public Member updateDtoToMember(MemberRequestDto.updateDto updateDto) {
        if ( updateDto == null ) {
            return null;
        }

        Member member = new Member();

        member.setMemberId( updateDto.getMemberId() );
        member.setPassword( updateDto.getPassword() );
        member.setNickName( updateDto.getNickName() );

>>>>>>> 16e2eeff3451663c8a95b0710e7f6a171cc22333
        return member;
    }

    @Override
    public MemberResponseDto.UpdateDto memberToUpdateDto(Member member) {
        if ( member == null ) {
            return null;
        }

        MemberResponseDto.UpdateDto.UpdateDtoBuilder updateDto = MemberResponseDto.UpdateDto.builder();

        updateDto.nickName( member.getNickName() );
        updateDto.memberId( member.getMemberId() );

        return updateDto.build();
    }
}
