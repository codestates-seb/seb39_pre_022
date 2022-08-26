package com.Team22.preproject.StackOverFlow.answer.entity;

import com.Team22.preproject.StackOverFlow.member.entity.Member;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "LIKES")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long likeId;

    private byte likes;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "ANSWER_ID")
    private Answer answer;

    public void addMember(Member member) {
        this.member = member;
    }

    public void addAnswer(Answer answer){
        this.answer = answer;
    }
}
