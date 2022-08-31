import React from "react";
import styled from "styled-components";
import Questions from "./Questions";
//import dummy from "../mockData.json";
//글 목록 li

export const Ul = styled.ul`
display: flex;
justify-content: center;
align-items: center;
//background-color: aqua;

li{
  display: list-item;
  width: 70%;
  list-style: none;
  border-top: solid 1px lightgray;
  margin: auto;
  padding: 0 1rem;
  //background-color: pink;
}
h2{
  color: #0074cc;
  font-weight: 400;
  margin-bottom: 0;
}
.profile{
  text-align: end;
}
`;  

const Post = ({ posts, loading }) => {
 return (
    <>
      <Ul>
      {loading && <div> loading... </div>}
      <ul >
        {posts.map((post) => (
          <li key={post.id}>
            <h2 to="/questions" element={<Questions/>}>{post.title}</h2>
            <p className="postBody">{post.body}</p>
            <p className="profile">username: {post.userId}</p>
            
            </li>
          
          ))}
          
      </ul>
      </Ul>
    </>
  );
};
export default Post;