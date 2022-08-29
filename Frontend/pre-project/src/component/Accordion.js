import React from "react";
import styled from "styled-components";

export default function Accordion(props) {
    const [show, setShow] = React.useState(false);

    const openHandler = () => {
        setShow(!show)
    }

    return (
        <AccordionList>
            <li onClick={openHandler} className='list'>
                <div className="title_container">
                    <div className="title">
                        <span className="num">{props.num} </span>
                        <span>{props.title}</span>
                    </div>
                    <button className={show ? 'open_btn' : 'close_btn'} ></button>
                </div>
                <div>{show && props.list}</div>
            </li >

        </AccordionList>
    )
}

const AccordionList = styled.div`
width: 24rem;
height: auto;
padding: 1rem;
margin-left: -1rem;
background: white;
display: flex;
flex-direction: column;
box-shadow: 1rem 1rem 1rem rgba(211,212,213,0.5);
border-bottom-left-radius: 0.5rem;
border-bottom-right-radius: 0.5rem;
margin-top: -1rem;

    .open_btn{
        background: url('https://raw.githubusercontent.com/eirikmadland/notion-icons/master/v5/icon3/mt-keyboard_arrow_down.svg');
        background-size: cover;
        width: 2rem;
        height: 2rem;
        border: none;
    }

    .close_btn{
        background: url('https://raw.githubusercontent.com/eirikmadland/notion-icons/master/v5/icon3/mt-keyboard_arrow_up.svg');
        background-size: cover;
        width: 2rem;
        height: 2rem;
        border: none;
    }

    .list{
        background-color: white;
        height: auto;
        list-style: none;
        cursor: pointer;
    }

    .num{
        color: rgb(67,147,247);
        font-weight: 800;
        text-shadow: 0.1rem 0.05rem 0.1rem rgba(67,147,247,0.5)
    }

    .title_container{
        display: flex;
        justify-content: space-between;
        align-items: center;

    }

    button{
        cursor: pointer;
    }
`