package com.Team22.preproject.StackOverFlow.auth;


import com.Team22.preproject.StackOverFlow.member.entity.Member;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Data
public class MemberDetails implements UserDetails {
    private long memberId;
    private String email;
    private String nickName;
    private String roles;

    public MemberDetails(long memberId,
                         String email,
                         String nickName,
                         String roles) {
        this.memberId = memberId;
        this.nickName = nickName;
        this.email = email;
        this.roles = roles;
    }
//    public static MemberDetails create(Member member) {
//        Collection<GrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority(member.getRoles()));
//
//        return new MemberDetails(member.getMemberId(), member.getEmail(), member.getNickName(),"USER");
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}


