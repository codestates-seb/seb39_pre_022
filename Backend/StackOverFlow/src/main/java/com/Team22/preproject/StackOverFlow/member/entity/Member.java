package com.Team22.preproject.StackOverFlow.member.entity;

import com.Team22.preproject.StackOverFlow.answer.entity.Answer;
import com.Team22.preproject.StackOverFlow.audit.Auditable;
import com.Team22.preproject.StackOverFlow.comment.entity.AnswerComment;
import com.Team22.preproject.StackOverFlow.comment.entity.QuestionComment;
import com.Team22.preproject.StackOverFlow.question.entity.Question;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@NoArgsConstructor
public class Member extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long memberId;

    @Column(unique = true, length = 200)
    private String email;

    @JsonIgnore
    private String password;

    private String nickName;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Question> questions = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<QuestionComment> questionComments= new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Answer> answers = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<AnswerComment> answerComments = new ArrayList<>();

//    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
//    private List<Like> likes = new ArrayList<>();

    public void addAnswer(Answer answer){
        if(answer != null && !answers.contains(answer)){
            answer.addMember(this);
            this.answers.add(answer);
        }
    }
    public void addQuestion(Question question){
        if(question != null && !this.questions.contains(question)){
            question.addMember(this);
            this.questions.add(question);
        }
    }

    public void addQuestionComment(QuestionComment questionComment){
        if(questionComment != null && !this.questionComments.contains(questionComment)){
            questionComment.addMember(this);
            this.questionComments.add(questionComment);
        }
    }

    public void addAnswerComment(AnswerComment answerComment){
        if (answerComment != null && !this.answerComments.contains(answerComment)){
            answerComment.addMember(this);
            this.answerComments.add(answerComment);
        }
    }
}
