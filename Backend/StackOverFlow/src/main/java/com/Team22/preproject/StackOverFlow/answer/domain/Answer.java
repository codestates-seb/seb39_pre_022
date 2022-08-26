package com.Team22.preproject.StackOverFlow.answer.domain;

import com.Team22.preproject.StackOverFlow.comment.domain.AnswerComment;
import com.Team22.preproject.StackOverFlow.like.domain.Like;
import com.Team22.preproject.StackOverFlow.member.domain.Member;
import com.Team22.preproject.StackOverFlow.question.domain.Question;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Answer {

    // Answer 답변의 PK와 FK들
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long answerId;

    @ManyToOne
    @JoinColumn(name="QUESTION_ID")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "answer")
    private List<AnswerComment> answerComments = new ArrayList<>();

    @Length(min = 30, max=300)
    @Column(nullable = false)
    private String answer;

    @OneToMany(mappedBy = "answer")
    private List<Like> likes = new ArrayList<>();

    // Like를 합친 값입니다. likeCount도 괜찮을 것 같습니다.
    private int voteCount = 0;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime modifiedAt = LocalDateTime.now();


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
