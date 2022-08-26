import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import logo from '../img/stack-overflow-logo.png';
import Navbar from './Navbar';
import axios from 'axios';

const SignupContainer = styled.div`
.logo{
    width: 2rem;
    margin-top: 7rem;
    padding: 1rem;
}

.container {
  margin: 0 auto;
  border: 1px solid black;
  width: 30rem;
  padding: 1rem;
}

.login-form {
  display: flex;
  flex-direction: column;
  padding: 2rem;
  text-align: left;
}

.login-form input,
.login-form button {
  margin: 0.5rem;
  margin-bottom: 1rem;
  height: 3rem;
}

.login-form label{
    padding: 0 0.5rem;
}
.nohaveid {
  /* border: 1px solid black; */
  display: flex;
  flex-direction: column;
  margin-bottom: 1rem;
}

button{
    border: none;
    border-radius: 0.2rem;
    background-color: rgb(1, 153, 255);
    color: white;
    font-weight: 900;
    font-size: 1.1rem;
}
`

export default function SignUp() {
    const [nickName, setNickName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();


    const check = () => {
        navigate('/login')
    }

    // axios
    //     .post('http://localhost:8080/members', {
    //         nickname: nickName,
    //         email: email,
    //         password: password,
    //     })
    //     .then((res) => {
    //         console.log(res)
    //         console.log('회원가입 성공');
    //         navigate('/login')
    //     })
    //     .catch((err) => {
    //         console.log(err)
    //         console.log('회원가입 실패');
    //     });


    return (
        <SignupContainer>
            <Navbar />
            <Link to='/'><img className='logo' src={logo} alt='stack-overflow-logo' /></Link>
            <div className='container'>
                <form onSubmit={(e) => e.preventDefault()} className='login-form'>
                    <label htmlFor='userName'>Username</label>
                    <input
                        type='text'
                        id='userName'
                        placeholder='이름을 입력해주세요'
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
                        placeholder='Email을 입력해주세요'
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
                        placeholder='비밀번호를 입력해주세요'
                        autoComplete='off'
                        value={password}
                        onChange={(event) => setPassword(event.target.value)}
                        required
                    >
                    </input>
                    <button onClick={() => { check() }}>Sign up</button>
                </form>
            </div>
        </SignupContainer>
    )
}