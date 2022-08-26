package com.Team22.preproject.StackOverFlow.comment.domain;

import com.Team22.preproject.StackOverFlow.member.domain.Member;
import com.Team22.preproject.StackOverFlow.question.domain.Question;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class QuestionComment {
    // PK와 FK들
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long question_comment_id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "QUESTION_ID")
    private Question question;


    // Question Comment 질문 전용 댓글으 속성들
    @Column(nullable = false, length = 300)
    private String questionComment;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime modifiedAt = LocalDateTime.now();

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
