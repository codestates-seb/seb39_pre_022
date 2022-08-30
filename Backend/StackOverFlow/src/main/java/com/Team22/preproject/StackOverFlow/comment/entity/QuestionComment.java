package com.Team22.preproject.StackOverFlow.comment.entity;

import com.Team22.preproject.StackOverFlow.audit.Auditable;
import com.Team22.preproject.StackOverFlow.member.entity.Member;
import com.Team22.preproject.StackOverFlow.question.entity.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class QuestionComment extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long questionCommentsId;

    @Column(nullable = false)
    @Length(max = 300)
    private String questionComments;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "QUESTION_ID")
    private Question question;

    public void addMember(Member member) {
        if(this.member == null && member != null){
            this.member = member;
        }
    }

    public void addQuestion(Question question) {
        if(this.question == null && question != null){
            this.question = question;
        }
    }
}
