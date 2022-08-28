package com.Team22.preproject.StackOverFlow.like.entity;

import com.Team22.preproject.StackOverFlow.answer.entity.Answer;
import com.Team22.preproject.StackOverFlow.member.entity.Member;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;

@Data
@Entity(name="LIKES")
@NoArgsConstructor
public class Like {
    // PK와 FK들
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long LikeId;
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name="ANSWER_ID")
    private Answer answer;

    // Like의 추천 속성

    @Range(min=-1, max=1) // 최소값 -1 최댓 값 1로 설정합니다.
    @Column(columnDefinition = "TinyInt default 0")  // 아무 값도 없이 넘어오면 기본값을 0으로 합니다. 데이터베이스에서 TinyInt(-127~128 을 갖게 합니다.)
    private byte vote = 0; // like는 데이터베이스 함수 예약어이기 때문에 피했습니다.


    // 참조 추가기능
    public void addAnswer(Answer answer){
        if(this.answer == null && answer != null){
            this.answer = answer;
        }
    }

    public void addMember(Member member) {
        if(this.member == null && member != null){
            this.member = member;
        }
    }
}
