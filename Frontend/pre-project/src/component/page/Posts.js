import { Link } from 'react-router-dom';
import React, { useState, useEffect } from "react";
import axios from "axios";
import Post from "./Post";
import Navbar from "../Navbar";
import styled from "styled-components";

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
  // eslint-disable-next-line no-unused-vars
  const [currentPage, setCurrentPage] = useState(1);
  const [postsPerPage] = useState(1);

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
      <Navbar />
      <TopQuestions>
             <section>
                <span className='question_content'>Top Questions</span><Link to='/askquestion'><button className='ask_btn'>Ask Question</button></Link>
            </section>
            </TopQuestions>
      <Post posts={currentPosts(posts)} loading={loading}></Post>
      
      {/* <Link to='/question'>plz help me</Link> */}
    </div>
  );
}
export default Posts;