import React, { useState } from 'react';
import Navbar from './Navbar';
import { Link, useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import moment from 'moment';
import Accordion from './Accordion';
import { CKEditor } from '@ckeditor/ckeditor5-react';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';

export default function AskQuestion() {
    const [title, setTitle] = useState('')
    const navigate = useNavigate();

    // moment 라이브러리로 현재 시간 확인 가능
    const time = moment().format('YYYY.MM.DD HH:mm:ss');
    console.log(time)

    const list1 = (
        <ul className='acc_item'>
            <li>Include details about your goal</li>
            <li>Describe expected and actual results</li>
            <li>Include any error messages</li>
        </ul>
    )

    const list2 = (
        <p>
            Show what you’ve tried and tell us wh\at you found (on this site or elsewhere) and why it didn’t meet your needs. You can get better answers when you provide research.
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
                        <p>Be specific and imagine you’re asking a question to another person</p>
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
                        <p>Include all the information someone would need to answer your question</p>
                        <CKEditor
                            editor={ClassicEditor}
                            data="<p>Write you question</p>"
                            onChange={(event, editor) => {
                                const data = editor.getData();
                                console.log(data)
                            }}
                            onFocus={(event, editor) => {
                                console.log('Focus.', editor);
                            }}
                        />
                    </form>
                    <Link to='/question'><button className='que_btn'>Review your question</button></Link>
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
height: 100vh;
text-align: left;
display: flex;
flex-direction: column;
align-items: center;

h1{
    font-weight: 500;
    display: flex;
    width: 61rem;
    margin-bottom: 3rem;
}

section{
    display: flex;
    justify-content: center;
    width: 62rem;

    .ask-form{
        display: flex;
        flex-direction: column;
        background: white;
        padding: 1rem;
        margin-right: 1.5rem;
        width: 36rem;
        box-shadow: 0.1rem 0.1rem 1rem rgba(211,212,213,0.5);
        border-radius: 0.5rem;

        label{
            font-weight: bold;
        }

        input{
            margin-bottom: 1rem;
            height: 2rem;
            border-radius: .2rem;
            border: 0.1rem solid rgb(211,212,213);
            padding: 0 .5rem;
        }

        .ck-content{
            height: 15rem;
        }

        p{
            font-size: .8rem;
            color: rgb(61,64,68);
            margin: .5rem 0;
        }
    }

    .que_btn{
    margin-top: 2rem;
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
        height: 12rem;
        background-color: white;
        width: 24rem;
        padding: 1rem;
        border-radius: 0.5rem;
        box-shadow: 0.1rem 0.1rem 1rem rgba(211,212,213,0.5);
    }
}

`