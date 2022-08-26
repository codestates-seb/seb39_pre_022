import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import logo from '../img/stack-overflow-logo.png';
import Navbar from './Navbar';
import data from '../data/data.json'; //로그인 확인용 데이터
import axios from 'axios';

const LoginContainer = styled.div`
/* border: 1px solid black; */

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
`;

export default function Login() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    // 로그인 확인용 데이터
    const user = data.user
    console.log(user)

    const check = () => {
        for (let i = 0; i < user.length; i++) {
            if (email === user[i].email && password === user[i].password) {
                navigate('/')
                return;
            }
        }
        alert('fail')
    }
    // 로그인 확인용 데이터


    // BE 통신때
    // axios.post('http://localhost:8080/members/login', {
    //     data: {
    //         email: email,
    //         password: password
    //     }
    // },
    //     // cookie 에 token을 발급할때 설정
    //     { withCredentails: true }
    // )
    //     .then((res) => {
    //         console.log(res);
    //         console.log(res.data);
    //         console.log('success');
    //         navigate('/');
    //     })
    //     .catch((err) => {
    //         console.log(err)
    //         console.log('error');
    //     });


    return (
        <LoginContainer>
            <Navbar />
            <Link to='/'><img className='logo' src={logo} alt='stack-overflow-logo' /></Link>
            <div className='container'>
                <form onSubmit={(e) => e.preventDefault()} className='login-form'>
                    <label htmlFor='userEmail'>Email</label>
                    <input
                        type='text'
                        id='userEmail'
                        placeholder='Email을 입력해주세요'
                        // 자동완성 기능 해제
                        autoComplete='off'
                        value={email}
                        onChange={(event) => setEmail(event.target.value)}
                        // 입력 안 했을 때 경고창
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
                    <button onClick={() => { check() }}>Log in</button>
                </form>
                <div className='nohaveid'>
                    don't you have account?
                    <Link to='/signup'>go to sign up</Link>
                </div>
            </div>
        </LoginContainer>
    )
}