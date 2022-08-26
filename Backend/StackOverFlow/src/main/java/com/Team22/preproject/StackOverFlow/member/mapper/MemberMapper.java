package com.Team22.preproject.StackOverFlow.member.mapper;

import com.Team22.preproject.StackOverFlow.member.dto.MemberRequestDto;
import com.Team22.preproject.StackOverFlow.member.dto.MemberResponseDto;
import com.Team22.preproject.StackOverFlow.member.entity.Member;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    Member signUpDtoToMember(MemberRequestDto.singUpDto singUpDto);

    Member loginDtoToMember(MemberRequestDto.loginDto loginDto);

    MemberResponseDto.MemberInfo memberToMemberInfo(Member member);

    Member updateDtoToMember(MemberRequestDto.updateDto updateDto);

    MemberResponseDto.UpdateDto memberToUpdateDto(Member member);
}
