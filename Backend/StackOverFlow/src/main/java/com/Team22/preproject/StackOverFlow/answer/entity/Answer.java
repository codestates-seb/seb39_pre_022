package com.Team22.preproject.StackOverFlow.answer.entity;

import com.Team22.preproject.StackOverFlow.comment.entity.AnswerComment;
import com.Team22.preproject.StackOverFlow.member.entity.Member;
import com.Team22.preproject.StackOverFlow.question.entity.Question;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long answerId;

    @NotBlank
    @Column(nullable = false)
    @Length(min = 30, max = 300)
    private String answer;

    private int likeCount = 0;

    @Column(nullable = false, name = "FIRST_CREATED_AT")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false, name = "LAST_MODIFIED_AT")
    private LocalDateTime modifiedAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "QUESTION_ID")
    private Question question;

    @OneToMany(mappedBy = "answer", cascade = CascadeType.ALL)
    private List<AnswerComment> answerComments = new ArrayList<>();

    @OneToMany(mappedBy = "answer", cascade = CascadeType.ALL)
    private List<Like> likes = new ArrayList<>();

    public void addLike(Like like) {
        if(like != null && !this.likes.contains(like)){
            like.addAnswer(this);
            this.likes.add(like); // 코드의 this 인자를 보고 현재 클래스의 likes 배열에서 추가한 다는 것을 나타내는 명시적인 효과를 위해 적었습니다.
        }
    }

    public void addAnswerComment(AnswerComment answerComment){
        if(answerComment != null && !this.answerComments.contains(answerComment)){
            answerComment.addAnswer(this);
            this.answerComments.add(answerComment);
        }
    }

    public void addQuestion(Question question) {
        if(this.question == null && question != null){
            this.question = question;
        }
    }
    public void addMember(Member member) {
        if(this.member == null && member != null){
            this.member = member;
        }
    }
}
