import { Link } from 'react-router-dom';
import React, { useState, useEffect } from "react";
import axios from "axios";
import Post from "./Post";
import Pagination from "./Pagination";
import styled from 'styled-components';

const TopQuestions = styled.div`
section{
 //padding: 1rem 0;
  display: flex;
  text-align: center;
  width: 70%;
  margin: auto;
  //background-color: aliceblue;
}
`

function Posts() {
  const [posts, setPosts] = useState([]);
  const [loading, setLoading] = useState(false);
  const [currentPage, setCurrentPage] = useState(1);
  const [postsPerPage] = useState(10);
  // 제한하고 싶은 게시물의 갯수.

  useEffect(() => {
    const fetchData = async () => {
      setLoading(true);
      const response = await axios.get(
        "https://jsonplaceholder.typicode.com/posts"
      );
      setPosts(response.data);
      setLoading(false);
    };
    fetchData();
  }, []);

  const indexOfLast = currentPage * postsPerPage;
  const indexOfFirst = indexOfLast - postsPerPage;
  const currentPosts = (posts) => {
    let currentPosts = 0;
    currentPosts = posts.slice(indexOfFirst, indexOfLast);
    return currentPosts;
  };

  return (
    <div className="Posts">
             <TopQuestions>
             <section>
                <span className='question_content'>Top Questions</span><Link to='/askquestion'><button className='ask_btn'>Ask Question</button></Link>
            </section>
            </TopQuestions>
            {/* <Link to='/question'>plz help me</Link> */}
      <Post posts={currentPosts(posts)} loading={loading}></Post>
      <Pagination
        postsPerPage={postsPerPage}
        totalPosts={posts.length}
        paginate={setCurrentPage}
      ></Pagination>
    </div>
  );
}
export default Posts;