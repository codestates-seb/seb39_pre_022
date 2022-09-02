import React from "react";
import { Link } from "react-router-dom";
import styled from "styled-components";
export const Ul = styled.ul`
display: flex;
justify-content: center;
align-items: center;
padding: 0;
ul{padding:0;}
li{
  display: list-item;
  width: 70%;
  list-style: none;
  border-top: solid 1px lightgray;
  margin: auto;
  padding: 0 1rem;
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
    <Ul>
      {loading && <div> loading... </div>}
      <ul>
        {posts.map((post) => (
          <li key={post.id}>
            <h2 ><Link to='/questions'>{post.title}</Link></h2>
            <p className="postBody">{post.body}</p>
            <p className="profile">username: {post.userId}</p>

          </li>
        ))}
      </ul>
    </Ul>
  );
};
export default Post;