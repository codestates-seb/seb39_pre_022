package com.Team22.preproject.StackOverFlow.member.mapper;

import com.Team22.preproject.StackOverFlow.member.dto.MemberRequestDto;
import com.Team22.preproject.StackOverFlow.member.dto.MemberResponseDto;
import com.Team22.preproject.StackOverFlow.member.entity.Member;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    Member signUpDtoToMember(MemberRequestDto.singUpDto singUpDto);

    Member loginDtoToMember(MemberRequestDto.loginDto loginDto);

    default MemberResponseDto.MemberInfo memberToMemberInfo(Member member){
        return MemberResponseDto.MemberInfo.builder()
                .email(member.getEmail())
                .nickName(member.getNickName())
                .build();
    }
}
