package com.Team22.preproject.StackOverFlow.comment.domain;

import com.Team22.preproject.StackOverFlow.answer.domain.Answer;
import com.Team22.preproject.StackOverFlow.member.domain.Member;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
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
