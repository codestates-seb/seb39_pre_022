import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import Navbar from './Navbar';

export default function SignUp() {
    const url = 'http://localhost:8080/members/signup';
    const [nickName, setNickName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const signUp = () => {
        fetch(url, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                nickName: nickName,
                email: email,
                password: password,
            })
        })
            .then((res) => {
                console.log('respnose:', res)
                if (res.status === 200) {
                    navigate('/login')
                }
            })
            .catch((err) => {
                console.log('error:', err)
            });
    }

    return (
        <SignupContainer>
            <Navbar />
            <section>
                <div className='contents'>
                    <h1>Join the Stack Overflow community</h1>
                    <ul>
                        <li>Get unstuck - ask a question</li>
                        <li>Unlock new privileges like voting and commenting</li>
                        <li>Save your favorite tags, filters, and jobs</li>
                        <li>Earn reputation and badges</li>
                    </ul>
                    <p>Collaborate and share knowledge with a private group for FREE.</p>
                    <p className='blue'>Get Stack Overflow for Teams free for up to 50 users.</p>
                </div>
                <div className='container'>
                    <form onSubmit={(e) => e.preventDefault()} className='signup-form'>
                        <label htmlFor='userName'>Display name</label>
                        <input
                            type='text'
                            id='userName'
                            autoComplete='off'
                            value={nickName}
                            onChange={(event) => setNickName(event.target.value)}
                            required
                        >
                        </input>
                        <label htmlFor='userEmail'>Email</label>
                        <input
                            type='text'
                            id='userEmail'
                            autoComplete='off'
                            value={email}
                            onChange={(event) => setEmail(event.target.value)}
                            required
                        >
                        </input>
                        <label htmlFor='userPassword'>password</label>
                        <input
                            type='password'
                            id='userPassword'
                            autoComplete='off'
                            value={password}
                            onChange={(event) => setPassword(event.target.value)}
                            required
                        >
                        </input>
                        <button onClick={signUp}>Sign up</button>
                    </form>
                    <div className='haveid'>
                        <span>Already have an account?<Link to='/login'>Log in</Link></span>
                    </div>
                </div>
            </section>

        </SignupContainer>
    )
}

const SignupContainer = styled.div`
background: rgb(241, 242, 243);
height: 100vh;

section{
    margin-top: 13rem;
    display: flex;
    justify-content: center;
    align-items: center;

    .contents{
        display: flex;
        flex-direction: column;
        justify-content: start;

        h1{
            font-weight: 400;
            font-size: 1.8rem;
        }

        ul{
            text-align: left;
            margin-left: -2rem;

            li{
                list-style: none;
                margin-bottom: 1.5rem;
            }

            li:nth-last-child(1){
                margin-bottom: 0;
            }
        }

        p{
            color: rgb(106, 115, 124);
            text-align: left;
            margin-left: .5rem;
            font-size: .8rem;
        }

        .blue{
            margin-top: -.5rem;
            color: rgb(4, 113, 201);
        }
    }

    .container{
        .signup-form{
            border-radius: 0.5rem;
            background: white;
            box-shadow: 0.1rem 0.1rem 1rem rgba(211,212,213,0.5);
            width: 17rem;
            display: flex;
            flex-direction: column;
            padding: 2rem 1.5rem;
            text-align: left;
            font-weight: 600;
            margin: 2rem;
        }

        .signup-form input{
            margin: 0.5rem;
            margin-bottom: 1rem;
            height: 2rem;
            border-radius: .2rem;
            border: 0.1rem solid rgb(211,212,213);
            }

        .signup-form label{
            padding: 0 0.5rem;
        }

        button{
            margin: 0.5rem;
            margin-top: -.3rem;
            height: 2.5rem;
            border: none;
            border-radius: 0.2rem;
            background-color: rgb(16, 143, 249);
            color: white;
            font-weight: 900;
            box-shadow: inset 0 1px 0 0 hsl(0deg 0% 100% / 40%);
        }

        button:hover{
            background-color: rgb(4, 113, 201);
        }

        .haveid {
            display: flex;
            flex-direction: column;
            margin-bottom: 1rem;

            span{
                font-size: .8rem;

                a{
                    color: rgb(4, 113, 201);
                    padding: .5rem;
                    text-decoration: inherit;
                }

                a:hover{
                    color: rgb(25, 150, 250);
                }
            }
        }
    }
}
`