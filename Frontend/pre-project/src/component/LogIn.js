import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import logo from '../img/stack-overflow-logo.png';
import Navbar from './Navbar';

export default function Login() {
    const url = 'http://localhost:8080/members/login';
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const login = () => {
        fetch(url, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json', },
            body: JSON.stringify({
                email: email,
                password: password,
            })
        })
            .then((res) => {
                // console.log(res)
                console.log('로그인 성공');
                navigate('/')
            })
            .catch((err) => {
                console.log(err)
                console.log('로그인 실패');
            });
    }


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
                        autoComplete='off'
                        value={password}
                        onChange={(event) => setPassword(event.target.value)}
                        required
                    >
                    </input>
                    <button onClick={login}>Log in</button>
                </form>
            </div>
            <div className='nohaveid'>
                <span>Don't you have an account?<Link to='/signup'>Sign up</Link></span>
            </div>
        </LoginContainer>
    )
}

const LoginContainer = styled.div`
/* border: 1px solid black; */
background: rgb(241, 242, 243);
height: 100vh;

.logo{
    width: 2rem;
    margin-top: 11rem;
    padding: 1.5rem;
}

.container {
  margin: 0 auto;
  /* border: 1px solid black; */
  border-radius: 0.5rem;
  background: white;
  box-shadow: 0.1rem 0.1rem 1rem rgba(211,212,213,0.5);
  width: 20rem;
  /* padding: 1rem; */
}

.login-form {
  display: flex;
  flex-direction: column;
  padding: 2rem 1.5rem;
  text-align: left;
  font-weight: 600;
}

.login-form input{
  margin: 0.5rem;
  margin-bottom: 1rem;
  height: 2rem;
  border-radius: .2rem;
  border: 0.1rem solid rgb(211,212,213);
}

.login-form label{
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

.nohaveid {
  /* border: 1px solid black; */
  display: flex;
  flex-direction: column;
  margin-bottom: 1rem;

  span{
    font-size: .8rem;
      padding: 2rem;

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
`;