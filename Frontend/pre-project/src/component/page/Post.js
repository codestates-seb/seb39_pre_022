import React from "react";
import styled from "styled-components";
import Vote from "./Vote";


export const Ul = styled.ul`
  display: flex;
  justify-content: center;
  align-items: center;
span{
  cursor: pointer;
}
  li {
    display: list-item;
    width: 70%;
    list-style: none;

    margin: auto;
    padding: 0 1rem;
  }
  h1 {
    color: darkslategrey;
    font-weight: 400;
    margin-bottom: 0;
    cursor: pointer;
  }
  .profile {
    text-align: end;
  }
  code {
    display: block;
    padding: 1rem;
    background-color: #f6f6f6;
    color: #2f3337;
    border-radius: 5px;
    font-family: "Consolas", "Sans Mono", "Courier", "monospace";
    font-size: 0.75rem;
  }
`;
export const QnAcontainer = styled.div`
  display: flex;
`;
export const Qcontainer = styled.div`
.body-Q{
  padding: 2rem 0;
}
  //background-color:beige;
  .footer-Q {
    padding-top: 1rem;
    padding-bottom: 3rem;
    display: flex;
    justify-content: space-between;
    border-bottom: 1px solid lightgrey;
  }
  .aditBox-Q {
    // [수정 + 프로필]
    color: #6a737c;
    padding: 4px 0;
    & span {
      margin-right: 9px;
    }
    & span:hover {
      color: #828c96;
    }
  }
  .profile-Q {
    border-radius: 3px;
    background-color: #daedff;
    width: 13rem;
    padding: 10px;
    color: grey;

    & .profileWrap {
      display: flex;
    }
    & .img {
      width: 2.4rem;
      height: 2.4rem;
      background-color: #6a737c;
      margin-right: 5px;
    }
  }

  .commentBox {
    border-bottom: .9px solid rgba(211, 211, 211, 0.779);
padding-bottom: 1rem;
    & .comment-Q {
      font-size: small;
      
      border-bottom: .9px solid rgba(211, 211, 211, 0.779);
      

      padding: .5rem 2rem;
      & span{
        color: #006fd7;
      }
    }
    & span {
     
      color: lightgrey;
    }
    & span:hover {
      color: lightskyblue;
    }
  }
`;
const addComment = () =>{alert(["add"])}
const Edit = () =>{alert(["Edit"])}

const Post = ({ posts, loading }) => {
  return (
    <>
      <Ul>
        {loading && <div> loading... </div>}
        <ul>
          {posts.map((post) => (
            <li key={post.id}>
              <h1>{post.title}</h1>
              <QnAcontainer>
                <Vote />
                <Qcontainer>
                  <p className="body-Q">{post.body}</p>
                  <code>{post.body}</code>
                  <div className="footer-Q">
                    <div className="aditBox-Q">
                      <span>Share</span>
                      <span onClick={()=> (Edit())}>Edit</span> 
                      
                      <span>Follow</span>
                    </div>
                    <div className="profile-Q">
                      Edit time
                      <div className="profileWrap">
                        <div className="img" />
                        Username: {post.userId}
                      </div>
                    </div>
                  </div>
                  <div className="commentBox">
                    <p className="comment-Q">
                      {post.body} - <span>username: {post.userId}</span>
                    </p>
                    <span onClick={()=> (addComment())}>Add a comment</span>
                  </div>
                </Qcontainer>
              </QnAcontainer>
                <h4>3 Answers</h4>
              <QnAcontainer>
                <Vote />
                <Qcontainer>
                  <p className="body-Q">{post.body}</p>
                  <code>{post.body}</code>
                  <div className="footer-Q">
                    <div className="aditBox-Q">
                      <span>Share</span>
                      <span onClick={()=> (Edit())}>Edit</span>
                      <span>Follow</span>
                    </div>
                    <div className="profile-Q">
                      Edit time
                      <div className="profileWrap">
                        <div className="img" />
                        Username: {post.userId}
                      </div>
                    </div>
                  </div>
                  <div className="commentBox">
                    <p className="comment-Q">
                      {post.body} - <span>username: {post.userId}</span>
                    </p>
                    <span onClick={()=> (addComment())}>Add a comment</span>
                  </div>
                </Qcontainer>
              </QnAcontainer>
              
            </li>
          ))}
        </ul>
      </Ul>
    </>
  );
};
export default Post;
