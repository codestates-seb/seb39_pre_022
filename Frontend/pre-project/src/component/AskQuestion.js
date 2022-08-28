import React, { useState } from 'react';
import Navbar from './Navbar';
import { Link, useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import moment from 'moment';

export default function AskQuestion() {
    const [title, setTitle] = useState('')
    const [body, setBody] = useState('')
    const navigate = useNavigate();

    // moment 라이브러리로 현재 시간 확인 가능
    const time = moment().format('YYYY.MM.DD HH:mm:ss');
    console.log(time)


    return (
        <>
            <Navbar />
            <AskContainer>
                <h1>Ask a public question</h1>
                <section>
                    <form className='ask-form'>
                        <label htmlFor='title'>Title</label>
                        <p>Be specific and imagine you’re asking a question to another person</p>
                        <input
                            type='text'
                            id='title'
                            placeholder='e.g Is there an R function for finding the index of an element in a vector'
                            autoComplete='off'
                            required
                            onChange={event => setTitle(event.target.value)}
                            value={title}
                        />
                        <label htmlFor='body'>Body</label>
                        <p>Include all the information someone would need to answer your question</p>
                        <input
                            type='text'
                            id='body'
                            autoComplete='off'
                            required
                            onChange={event => setBody(event.target.value)}
                            value={body}
                        />
                    </form>
                    <article>
                        도움말
                    </article>
                </section>
                <Link to='/question'><button>Review your question</button></Link>
            </AskContainer>
        </>
    )
}

const AskContainer = styled.div`
border: 1px solid black;
text-align: left;

section{
    border: 1px solid black;
    display: flex;
    justify-content: space-around;

    .ask-form{
        border: 1px solid black;
        display: flex;
        flex-direction: column;


        input{
        }
    }

    article{
        border: 1px solid black;
    }
}

`