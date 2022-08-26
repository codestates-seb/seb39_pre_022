package com.Team22.preproject.StackOverFlow.question.domain;

import com.Team22.preproject.StackOverFlow.answer.domain.Answer;
import com.Team22.preproject.StackOverFlow.comment.domain.QuestionComment;
import com.Team22.preproject.StackOverFlow.member.domain.Member;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Question {
    // PK와 FK들 또는 그래프 탐색을 위한 객체들
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long questionId;


    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
    @OneToMany(mappedBy = "question")
    private List<Answer> answers = new ArrayList<>();

    @OneToMany(mappedBy = "question")
    private List<QuestionComment> questionComments = new ArrayList<>();

    // Question의 속성들
    @Column(nullable = false)
    @Length(min=15, max=50)
    private String title;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime modifiedAt = LocalDateTime.now();


    public void addMember(Member member) {
        if(this.member == null && member != null){
            this.member = member;
        }
    }

    public void addAnswer(Answer answer) {
        if(!this.answers.contains(answer) && answer != null){
            answer.addQuestion(this);
            this.answers.add(answer);
        }
    }

    public void addQuestionComment(QuestionComment questionComment){
        if(questionComment != null && !this.questionComments.contains(questionComment)){
            this.questionComments.add(questionComment);
        }
    }

}
