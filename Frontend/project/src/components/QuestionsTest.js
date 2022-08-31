//import styled from 'styled-components';
import {useCallback, useEffect, useState} from 'react';
import axios from "axios";
import styled from "styled-components";
const Container = styled.div`
  padding: 30px 20px;
`;
const QuestionMeta = styled.div`
  display: grid;
  grid-template-columns: 1fr 1fr;
`;
const QuestionTitle = styled.h1`
  border-bottom: 1px solid rgba(255,255,255,.1);
  padding-bottom: 10px;
`;
const PostBody = styled.div`
  display: grid;
  grid-template-columns: 50px 1fr;
  column-gap: 20px;
  margin-bottom: 20px;
`;
const VotingButtons = styled.div`
`
function QuestionsTest({match}){
    const [question,setQuestion] = useState(false);
    const [userVote,setUserVote] = useState(0);
    const [voteCount,setVoteCount] = useState(0);
    //const [answers,setAnswers] = useState([]);

    const getQuestion = useCallback(() => {
        axios.get('http://localhost:3000/'+match.params.id)
          .then(response => {
            setQuestion(response.data.question);
            const voteSum = response.data.question.vote_sum;
            setVoteCount(voteSum === null ? 0 : voteSum);
            setUserVote(response.data.question.user_vote);
          });
      }, [match.params.id]);
      useEffect(() => {
        getQuestion();
      }, [getQuestion]);
    return (
        <>
          <Container>
            {question && (
              <>
                
                  <title>{question.title} - StackOvercloned</title>
               
                <QuestionTitle>{question.title}</QuestionTitle>
                <PostBody>
                  <VotingButtons style={{marginTop:'10px'}}
                                 initialTotal={voteCount}
                                 initialUserVote={userVote}
                                 postId={question.id} />
                  <div>
                    <QuestionMeta>
                  
                <div /* {question.created_at}{question.name || question.email} */ /> 
                    </QuestionMeta>
                  </div>
                </PostBody>
              </>
            )}
    
            
{/*     
            {answers.map(answer => (
              <div>
                <hr/>
                <PostBody>
                  <VotingButtons style={{marginTop:'10px'}}
                                 initialTotal={answer.votes_sum}
                                 initialUserVote={answer.user_vote}
                                 postId={answer.id} />
                  <div>
                   
                      {answer.created_at}
                      id={answer.author_id}{answer.user_name || answer.email}
                    
                  </div>
                </PostBody>
                
              </div>
            ))} */}

          </Container>
        </>
      );
}
    
    export default QuestionsTest;
    