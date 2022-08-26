package com.Team22.preproject.StackOverFlow.question.entity;

import com.Team22.preproject.StackOverFlow.answer.entity.Answer;
import com.Team22.preproject.StackOverFlow.member.entity.Member;
import com.Team22.preproject.StackOverFlow.comments.entity.QuestionComment;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long questionId;

    @Column(nullable = false, columnDefinition = "TEXT",length = 300)
    private String question;

    @Column(nullable = false, columnDefinition = "TEXT", length = 200)
    private String title;

    @Column(nullable = false, name = "FIRST_CREATED_AT")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false, name = "LAST_MODIFIED_AT")
    private LocalDateTime modifiedAt = LocalDateTime.now();

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
