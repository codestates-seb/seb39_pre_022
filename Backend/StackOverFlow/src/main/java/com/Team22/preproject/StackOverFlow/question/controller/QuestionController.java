package com.Team22.preproject.StackOverFlow.question.controller;


import com.Team22.preproject.StackOverFlow.dto.response.MultiResponseWithPageInfoDto;
import com.Team22.preproject.StackOverFlow.dto.response.SingleResponseWithMessageDto;
import com.Team22.preproject.StackOverFlow.member.entity.Member;
import com.Team22.preproject.StackOverFlow.question.dto.QuestionRequestDto;
import com.Team22.preproject.StackOverFlow.question.entity.Question;
import com.Team22.preproject.StackOverFlow.question.entity.SessionConst;
import com.Team22.preproject.StackOverFlow.question.mapper.QuestionMapper;
import com.Team22.preproject.StackOverFlow.question.service.QuestionService;
import com.Team22.preproject.StackOverFlow.session.SessionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.websocket.server.PathParam;
import java.util.List;

@Validated
@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;
    private final QuestionMapper mapper;
//    private final SessionManager sessionManager;
    /*
        db에서 memberId를 가져오려면 path에 memberId를 넣어주거나 body에 넣어줘야 한다 코드가 지저분하기 때문에 바꿔줘야한다
        만약 body에 넣어주면 get method에서는 잘 동작하지 않는다 get은 dto를 사용하지 않기 때문에 - 용호님이랑 의논
     */
    //질문 등록
    @PostMapping("/{member-id}")
    public ResponseEntity createQuestion(@Positive @PathVariable("member-id") long memberId, @RequestBody @Valid QuestionRequestDto.CreatedQuestionDto createdQuestionDto){
        createdQuestionDto.setMemberId(memberId);
//        Question question = mapper.createQuestionDtoToQuestion(createdQuestionDto);
//        questionService.createdQuestion(question);
        Question question = questionService.createdQuestion(mapper.createQuestionDtoToQuestion(createdQuestionDto));
        System.out.println("question = " + question);
        return new ResponseEntity<>(new SingleResponseWithMessageDto<>(mapper.createdDtoToQuestion(question),"CREATED"), HttpStatus.CREATED);
    }

    // 코드를 쓰는 여러 방식이 있습니다. 그래서 여러가지 방식으로 보여 드릴게요 첫 번째가 HttpServletRequest를 이용한 방식입니다.

//    @PostMapping
    public ResponseEntity createQuestionV2(@RequestBody @Valid QuestionRequestDto.CreatedQuestionDto createdQuestionDto, HttpServletRequest request){
//        Question question = mapper.createQuestionDtoToQuestion(createdQuestionDto);
//        questionService.createdQuestion(question);

        // request에는 분명히 sessionId가 저장이 되어있을 거에요 그래서 그 request sessionId를 이용해서 session을 받아옵니다.
        HttpSession session = request.getSession(false);
        // 그리고 그 session은 Map 형태로 String:Object 로 매핑되어서 저장되어 있습니다. 여기서 우리가 전에 미리 설정해둔 문자열 상수로 저장된 값을 꺼냅니다.
        // 이게 지저분 하면 또 다른 방법이 있습니다.

        Member member = (Member)session.getAttribute(SessionConst.LOGIN_MEMBER);

        Question question = questionService.createdQuestion(mapper.createQuestionDtoToQuestion(createdQuestionDto));
        System.out.println("question = " + question);
        return new ResponseEntity<>(new SingleResponseWithMessageDto<>(mapper.createdDtoToQuestion(question),"CREATED"), HttpStatus.CREATED);
    }

    @PostMapping // Spring framework가 reflexion 기술을 사용하여 코드를 조작해서 위의 코드와 동일한 코드를 생성합니다.
    public ResponseEntity createQuestionV3(@SessionAttribute(name=SessionConst.LOGIN_MEMBER)Member member ,@RequestBody @Valid QuestionRequestDto.CreatedQuestionDto createdQuestionDto){
//        Question question = mapper.createQuestionDtoToQuestion(createdQuestionDto);
//        questionService.createdQuestion(question);

        createdQuestionDto.setMemberId(member.getMemberId());
        Question question = questionService.createdQuestion(mapper.createQuestionDtoToQuestion(createdQuestionDto));
        System.out.println("question = " + question);
        return new ResponseEntity<>(new SingleResponseWithMessageDto<>(mapper.createdDtoToQuestion(question),"CREATED"), HttpStatus.CREATED);
    }



    //본인이 작성한 특정 질문 조회
    @GetMapping("/{member-id}/question/{question-id}")
    public ResponseEntity getQuestion(@Positive @PathVariable("member-id") long memberId,
                                        @Positive @PathVariable("question-id") long questionId){
        Question question = questionService.findQuestion(memberId, questionId);
        return new ResponseEntity(new SingleResponseWithMessageDto<>(mapper.questionToInfo(question),"SUCCESS"),HttpStatus.OK);
    }

    //본인이 작성한 질문 전체 조회
    @GetMapping("/{member-id}")
    public ResponseEntity getMyQuestions(@Positive @PathVariable("member-id") long memberId,
                                         @Positive @PathParam("page") int page,
                                         @Positive @PathParam("size") int size){
        Page<Question> pageQuestions = questionService.findMyQuestions(page-1,size,memberId);
        List<Question> questionList = pageQuestions.getContent();

        return new ResponseEntity(new MultiResponseWithPageInfoDto<>(mapper.questionToQuestionInfo(questionList),pageQuestions),HttpStatus.OK);
    }

    //질문 수정
    @PatchMapping("/{member-id}/question/{question-id}")
    public ResponseEntity updateQuestion(@Positive @PathVariable("member-id") long memberId,
                                         @Positive @PathVariable("question-id") long questionId,
                                         @RequestBody @Valid QuestionRequestDto.UpdateQuestionDto updateQuestionDto){
        updateQuestionDto.setQuestionId(questionId);
        updateQuestionDto.setMemberId(memberId);
        Question question = questionService.updateQuestion(mapper.updateQuestionDtoToQuestion(updateQuestionDto));

        return new ResponseEntity<>(new SingleResponseWithMessageDto<>(mapper.questionToInfo(question),"SUCCESS"),HttpStatus.OK);
    }

    //질문 삭제
    @DeleteMapping("/{member-id}/question/{question-id}")
    public ResponseEntity deleteQuestion(@Positive @PathVariable("member-id") long memberId,
                                         @Positive @PathVariable("question-id") long questionId){
        questionService.deleteQuestion(memberId,questionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    //전체 질문 조회(예비 메인)
    @GetMapping
    public ResponseEntity getQuestions(@Positive @PathParam("page") int page,
                                       @Positive @PathParam("size") int size,
                                       Question question){
        Page<Question> pageQuestions = questionService.findQuestions(page-1,size);
        List<Question> questionList = pageQuestions.getContent();

        return new ResponseEntity(new MultiResponseWithPageInfoDto<>(mapper.questionToQuestionInfoList(questionList),pageQuestions),HttpStatus.OK);
    }

    //main페이지
//    @GetMapping
//    public ResponseEntity getAllQuestionAndAnswer(@Positive @PathParam("page") int page,
//                                                  @Positive @PathParam("size") int size,
//                                                  Question question){
//        Page<Question> pageQuestions = questionService.findQuestions(page-1,size);
//        List<Question> questionList = pageQuestions.getContent();
//
//        return new ResponseEntity(new MultiResponseWithPageInfoDto<>(mapper.questionToQuestionAnswerInfoList(questionList),pageQuestions),HttpStatus.OK);
//    }

    //내용별 조회
}
