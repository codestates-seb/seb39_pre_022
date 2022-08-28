package com.Team22.preproject.StackOverFlow.comment.entity;


import com.Team22.preproject.StackOverFlow.answer.entity.Answer;
import com.Team22.preproject.StackOverFlow.member.entity.Member;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class AnswerComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long answerCommentsId;

    @Column(nullable = false)
    @Length(max = 300)
    private String answerComments;

    @Column(nullable = false, name = "FIRST_CREATED_AT")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false, name = "LAST_MODIFIED_AT")
    private LocalDateTime modifiedAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "ANSWER_ID")
    private Answer answer;

    public void addAnswer(Answer answer){
        if(answer != null && this.answer == null){
            this.answer = answer;
        }
    }

    public void addMember(Member member) {
        if(this.member == null && member != null){
            this.member = member;
        }
    }
}
