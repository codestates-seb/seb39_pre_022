package com.Team22.preproject.StackOverFlow.question.entity;

import com.Team22.preproject.StackOverFlow.answer.entity.Answer;
import com.Team22.preproject.StackOverFlow.audit.Auditable;
import com.Team22.preproject.StackOverFlow.member.entity.Member;
import com.Team22.preproject.StackOverFlow.comment.entity.QuestionComment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Question extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long questionId;

    @Column(nullable = false)
    @Length(max = 300)
    private String question;

    @Column(nullable = false)
    @Length(max = 300)
    private String title;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<QuestionComment> questionCommentsList = new ArrayList<>();

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> answerList = new ArrayList<>();

    public void addQuestionComments(QuestionComment questionComments){
        if(!this.questionCommentsList.contains(questionComments)){
            questionCommentsList.add(questionComments);
        }
    }

    public void addMember(Member member) {
        this.member = member;
    }
}
