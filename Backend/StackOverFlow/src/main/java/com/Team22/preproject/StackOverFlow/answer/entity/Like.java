package com.Team22.preproject.StackOverFlow.answer.entity;

import com.Team22.preproject.StackOverFlow.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.weaver.loadtime.definition.Definition;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "LIKES")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long likeId;

    @Range(min=-1, max=1)
    private byte vote;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "ANSWER_ID")
    private Answer answer;

    public void addMember(Member member) {
        if(this.member == null && !member.getLikes().contains(this)){
            this.member = member;
        }

    }

    public void addAnswer(Answer answer)
    {
        if(answer != null && this.answer == null)
        {
            answer.addLike(this);
            this.answer = answer;
        }
    }
}
