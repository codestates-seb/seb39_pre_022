package com.Team22.preproject.StackOverFlow.member.domain;

import com.Team22.preproject.StackOverFlow.answer.domain.Answer;
import com.Team22.preproject.StackOverFlow.comment.domain.QuestionComment;
import com.Team22.preproject.StackOverFlow.question.domain.Question;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Member {

    //Member의 PK와 FK들
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int memberId;

    @OneToMany(mappedBy = "member")
    private List<Question> questions = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Answer> answers = new ArrayList<>();


    // Member의 속성들
    @Email
    @Column(updatable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickName;

    @Column(updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime modifiedAt = LocalDateTime.now();

    // 객체 참조를 위한 메서드
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
}
