package com.Team22.preproject.StackOverFlow.member.repository;

import com.Team22.preproject.StackOverFlow.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
