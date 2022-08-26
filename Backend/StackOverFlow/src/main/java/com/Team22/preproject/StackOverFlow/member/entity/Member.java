package com.Team22.preproject.StackOverFlow.member.entity;

import com.Team22.preproject.StackOverFlow.answer.entity.Answer;
import com.Team22.preproject.StackOverFlow.answerComments.entity.AnswerComments;
import com.Team22.preproject.StackOverFlow.qeustionComments.entity.QuestionComments;
import com.Team22.preproject.StackOverFlow.question.entity.Question;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long memberId;

    @Column(unique = true, length = 200)
    private String email;

    @JsonIgnore
    private String password;

    private String nickName;

    @Column(nullable = false, name = "FIRST_CREATED_AT")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false, name = "LAST_MODIFIED_AT")
    private LocalDateTime modifiedAt = LocalDateTime.now();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Question> questionList = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<QuestionComments> questionCommentsList= new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Answer> answerList = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<AnswerComments> answerCommentsList = new ArrayList<>();

//    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
//    private List<Like> likes = new ArrayList<>();

    public void addQuestion(Question question){
        if(!this.questionList.contains(question)){
            this.questionList.add(question);
        }
    }

    public void addAnswer(Answer answer){
        if(!this.answerList.contains(answer)){
            this.answerList.add(answer);
        }
    }
}
