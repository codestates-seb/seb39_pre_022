import React from 'react';
import Navbar from './Navbar';
import Questions from './Questions';
import { Link } from 'react-router-dom';

export default function Main() {

    return (
        <>
            <Navbar />
            <apsn>Top Questions</apsn><Link to='/askquestion'><button>Ask Question</button></Link>
            <Questions />
        </>
    )
}