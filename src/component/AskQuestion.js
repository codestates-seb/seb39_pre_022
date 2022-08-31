import React, { useState } from 'react';
import Navbar from './Navbar';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import moment from 'moment';
import Accordion from './Accordion';
import { CKEditor } from '@ckeditor/ckeditor5-react';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';

export default function AskQuestion() {
    const url = 'http://localhost:8080/questoins/{member-id}';
    const [title, setTitle] = useState('')
    const [question, setQuestion] = useState('')
    const navigate = useNavigate();


    const ask = () => {
        fetch(url, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                title: title,
                question: question,
            })
        })
            .then((res) => {
                // console.log(res)
                console.log('질문 등록 성공');
                navigate('/question')
            })
            .catch((err) => {
                console.log(err)
                console.log('질문 등록 실패');
            });
    }
    // moment 라이브러리로 현재 시간 확인 가능
    const time = moment().format('YYYY.MM.DD HH:mm:ss');
    // console.log(time)

    const list1 = (
        <ul className='acc_item'>
            <li>Include details about your goal</li>
            <li>Describe expected and actual results</li>
            <li>Include any error messages</li>
        </ul>
    )

    const list2 = (
        <p>
            Show what you’ve tried and tell us what you found (on this site or elsewhere) and why it didn’t meet your needs. You can get better answers when you provide research.
        </p>
    )

    const list3 = (
        <p>
            When appropriate, share the minimum amount of code others need to reproduce your problem (also called a minimum, reproducible example)
        </p>
    )

    return (
        <AskContainer>
            <Navbar />
            <h1>Ask a public question</h1>
            <section>
                <article>
                    <form className='ask-form'>
                        <label htmlFor='title'>Title</label>
                        <p className='label'>Be specific and imagine you’re asking a question to another person</p>
                        <input
                            type='text'
                            id='title'
                            placeholder='e.g Is there an R function for finding the index of an element in a vector?'
                            autoComplete='off'
                            required
                            onChange={event => setTitle(event.target.value)}
                            value={title}
                        />
                        <label htmlFor='body'>Body</label>
                        <p className='label'>Include all the information someone would need to answer your question</p>
                        <CKEditor
                            editor={ClassicEditor}
                            onChange={(event, editor) => {
                                const data = editor.getData();
                                console.log(data)
                            }}
                            onFocus={(event, editor) => {
                                console.log('Focus.', editor);
                            }}
                        />
                    </form>
                    <button onClick={ask} className='que_btn'>Review your question</button>
                </article>
                <article className='accordion'>
                    <h2>Step 1:  Draft your question</h2>
                    <hr></hr>
                    <p>The community is here to help you with specific coding, algorithm, or language problems.
                        <br></br>
                        <br></br>
                        Avoid asking opinion-based questions.
                    </p>
                    <Accordion
                        num={`1.`}
                        title='Summarize the problem'
                        list={list1}
                    />
                    <Accordion
                        num={`2.`}
                        title={`Describe what you've tried`}
                        list={list2}
                    />
                    <Accordion
                        num={`3.`}
                        title='Show some code'
                        list={list3}
                    />
                </article>
            </section>
        </AskContainer>
    )
}

const AskContainer = styled.div`
background: rgb(241, 242, 243);
height: 100vh;

h1{
    /* border: 1px solid black; */
    display: flex;
    justify-content: space-around;
    font-weight:500;
    font-size: 1.7rem;
}

section{
    /* border: 1px solid red; */
    display: flex;
    justify-content: center;
    padding: 1rem;


    article{
        /* border: 1px solid black; */
        margin-right: 1rem;
        display: flex;
        flex-direction: column;
        border-radius: 0.5rem;

        .ask-form{
            background: white;
            display: flex;
            flex-direction: column;
            padding: 1rem;
            text-align: left;
            border-radius: 0.5rem;
            box-shadow: 0.1rem 0.1rem 1rem rgba(211,212,213,0.5);
        }

        label{
            font-weight: 500;
        }

        .label{
            font-size: .9rem;
            color: rgb(201,205,209);
            margin: .5rem 0;
        }

        input{
            height: 2rem;
            margin-bottom: 2rem;
            border: .1rem solid rgb(201,205,209);
            padding-left: .5rem;
            letter-spacing: .1rem;
        }

        .ck-content{
            height: 15rem;
            width: 33rem;
            font-size: .9rem;
            color: gray;
        }

        .ck.ck-dropdown.ck-heading-dropdown .ck-dropdown__button .ck-button__label {
            width: 4em;
        }

        .ck-file-dialog-button{
            display: none;
        }
    }

    .que_btn{
        margin-top: 2rem;
        width: 10rem;
        height: 2.5rem;
        border: none;
        border-radius: 0.2rem;
        background-color: rgb(16, 143, 249);
        color: white;
        font-weight: 900;
        box-shadow: inset 0 1px 0 0 hsl(0deg 0% 100% / 40%);
    }

    .que_btn:hover{
        background-color: rgb(4, 113, 201);
    }

    .accordion{
        background: white;
        width: 17rem;
        height: 15rem;
        text-align: left;
        padding: 1rem;
        flex-direction: column;
        box-shadow: 0.1rem 0.1rem 1rem rgba(211,212,213,0.5);

        h2{
            font-size: 1rem;
            margin: 0;
            margin-bottom: .5rem;
        }

        hr{
            width: 17rem;
        }

        p{
            font-size: .9rem;
        }
    }
}

`