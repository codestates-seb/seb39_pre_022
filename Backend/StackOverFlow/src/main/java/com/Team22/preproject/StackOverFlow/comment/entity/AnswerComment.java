package com.Team22.preproject.StackOverFlow.comment.entity;


import com.Team22.preproject.StackOverFlow.answer.entity.Answer;
import com.Team22.preproject.StackOverFlow.audit.Auditable;
import com.Team22.preproject.StackOverFlow.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class AnswerComment extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long answerCommentId;

    @Column(nullable = false)
    @Length(max = 300)
    private String answerComment;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "ANSWER_ID")
    private Answer answer;

    public void addAnswer(Answer answer){
        if(answer != null && this.answer == null){
            this.answer = answer;
            answer.addAnswerComment(this);
        }
    }

    public void addMember(Member member) {
        if(this.member == null && member != null){
            this.member = member;
            member.addAnswerComment(this);
        }
    }
}
