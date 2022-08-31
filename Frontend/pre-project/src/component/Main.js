import React from 'react';
import Navbar from './Navbar';
import Questions from './Questions';
import { Link } from 'react-router-dom';

export default function Main() {

    return (
        <>
            <Navbar />
            <span>Top Questions</span><Link to='/askquestion'><button>Ask Question</button></Link>
            <Questions />
            <Link to='/question'>plz help me</Link>
        </>
    )
}
