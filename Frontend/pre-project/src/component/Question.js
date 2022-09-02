import React from 'react';
import Questions from "./page/Posts";
import { Link, useNavigate } from 'react-router-dom';
import { CKEditor } from '@ckeditor/ckeditor5-react';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import styled from 'styled-components';

export default function Question() {
    const url = 'http://localhost:8080/questions/{question-Id}/answer';
    const [answer, setAnswer] = React.useState('')
    const navigate = useNavigate();

    const answerHander = () => {
        fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                answer: answer
            })
        })
            .then((res) => {
                console.log(res)
                console.log('답변 등록 성공');
                navigate('/question')
            })
            .catch((err) => {
                console.log('error:', err)
                console.log('답변 등록 실패');
            });
    }

    return (
        <Answer>
            {/* <Navbar />
            <hr></hr> */}
            <Link className='ask_Link' to='/askquestion'><button className='ask_btn'>Ask Question</button></Link>
            <div className='you'>
                <Questions />
            </div>
            <div className='answer-container'>
                <p>
                    Know someone who can answer? Share a link to this
                    <span> <Link to='/question'>question</Link></span> via email, Twitter, or Facebook.
                </p>
                <h1>
                    Your Answer
                </h1>
                <CKEditor
                    editor={ClassicEditor}
                    onChange={(event, editor) => {
                        const data = editor.getData();
                        console.log(data)
                        setAnswer(data)
                    }}
                    onFocus={(event, editor) => {
                        console.log('Focus.', editor);
                    }}
                />
                <button onClick={answerHander} className='answer_btn'>Post Your Answer</button>
            </div>
        </Answer>
    )
}

const Answer = styled.div`
/* border: 1px solid black; */
height: 100vh;
text-align: left;

.you{
    /* border: 1px solid black; */
    height: 20rem;
}

.answer-container{
    padding: 3rem;

    p{
        font-size: 1.2rem;
        margin-bottom: -.3rem;

        a{
            text-decoration: none;
            color: rgb(4, 113, 201);
        }

        a:hover{
            color:rgb(16, 143, 249)
        }
    }

    h1{
        font-size: 1.4rem;
        font-weight: 400;
        margin-bottom: 1.5rem;
    }

    .ck-content{
    height: 15rem;
    font-size: .8rem;
    color: gray;
}

.answer_btn{
    margin-top: 2rem;
    width: 9rem;
    height: 2.5rem;
    border: none;
    border-radius: 0.2rem;
    background-color: rgb(16, 143, 249);
    color: white;
    font-weight: 900;
    box-shadow: inset 0 1px 0 0 hsl(0deg 0% 100% / 40%);
    }

    .answer_btn:hover{
        background-color: rgb(4, 113, 201);
    }


}

.ask_Link{
    display: flex;
    justify-content: end;
    text-decoration: none;
}
.ask_btn{
        width: 8rem;
        height: 2.5rem;
        margin: 0 2rem;
        border: none;
        border-radius: 0.2rem;
        background-color: rgb(16, 143, 249);
        color: white;
        font-weight: 900;
        box-shadow: inset 0 1px 0 0 hsl(0deg 0% 100% / 40%);
    }

    .ask_btn:hover{
        background-color: rgb(4, 113, 201);
    }
`