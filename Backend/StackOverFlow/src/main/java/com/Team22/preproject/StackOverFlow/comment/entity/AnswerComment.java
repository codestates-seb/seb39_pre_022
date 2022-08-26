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
    // 답변 전용 댓글의 PK와 FK들
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long answerCommentId;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name="ANSWER_ID")
    private Answer answer;

    // Answer Comment 답변 전용 댓글의 속성들
    @Length(max=300)
    @Column(nullable = false)
    private String answerComment;

    @Column(nullable = false, name="FIRST_CREATED_AT")
    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime modifiedAt = LocalDateTime.now();

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
