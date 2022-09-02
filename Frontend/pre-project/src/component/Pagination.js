//import React from "react";
import React, { useState } from "react";
import styled from "styled-components";

const PageUl = styled.ul`
  list-style: none;
  text-align: center;
  border-radius: 3px;
  padding: 1px;
  color: rgb(83, 83, 83);
  background-color: white;
`;

const PageLi = styled.li`
  display: inline-block;
  font-size: 1.2rem;
  margin: 0 2px;
  padding: 0 8px;
  padding-top: 8px;
  min-width: 1rem; // 이거없으면 버튼크기 제각각으로 바뀜
  width: auto; // number 자릿수에 따라 버튼크기가 달라서 오토로 설정.
  height: 2rem;
  border-radius: 5px;
  border: 1px solid lightgray;

  &:hover {
    cursor: pointer;
    background-color: lightgray;
  }
&:active {
    //임시
    color: white;
    background-color:#f48225;
  }
`;

const Pagination = ({ postsPerPage, totalPosts, paginate }) => {
  const pageNumbers = [];
  for (let i = 1; i <= Math.ceil(totalPosts / postsPerPage); i++) {
    pageNumbers.push(i);
    
  }
  const [isActive, setIsActive] = useState(false);
  const handleClick = () => {
   
      setIsActive(!isActive);
      console.log(!isActive);
  };
  return (
    <div>
      <nav>
        <PageUl className="pagination">
          {pageNumbers.map((number) => (
            <PageLi
            key={number}
            onClick={() => {
              paginate(number)
              console.log(number);
            handleClick()

            }}
            // style={{
            //   backgroundColor: isActive ? "#f48225" : "",
            //   color: isActive ? "white" : "",
            // }}
              className="page-link">
              {number}
            </PageLi>
          ))}
        </PageUl>
      </nav>
    </div>
  );
};

export default Pagination;