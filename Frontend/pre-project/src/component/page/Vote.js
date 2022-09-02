import React, { useState } from "react";
import { AiFillCaretUp, AiFillCaretDown } from "react-icons/ai";
import styled from "styled-components";

export const VoteContainer = styled.div`
  text-align: center;

  .number {
    color: grey;
  }
  .up,
  .down {
    color: lightgrey;
    margin: -27px;
  }
`;
function Vote() {
  const [number, setNumber] = useState(0);

  const onIncrease = () => {
    setNumber((prevNumber) => prevNumber + 1);
  };

  const onDecrease = () => {
    setNumber((prevNumber) => prevNumber - 1);
  };

  return (
    <VoteContainer>
      <AiFillCaretUp className="up" onClick={onIncrease} size="60" />
      <h1 className="number">{number}</h1>
      <AiFillCaretDown className="down" onClick={onDecrease} size="60" />
    </VoteContainer>
  );
}

export default Vote;