import React from 'react';
import Navbar from './Navbar';
import Posts from './Posts'
//import { Link } from 'react-router-dom';
import styled from 'styled-components';

export default function Main() {
    return (
        <MainContainer>
            <Navbar />
            <Posts />
        </MainContainer>
    )
}

const MainContainer = styled.div`
/* border: 1px solid black; */
height: auto;

section{
    /* border: 1px solid black; */
    display: flex;
    justify-content: space-between;
    margin-top: 1rem;
    padding: 1rem;

    .question_content{
        /* border: 1px solid black; */
        font-size: 2rem;
        padding: 0 3rem;
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
}
`