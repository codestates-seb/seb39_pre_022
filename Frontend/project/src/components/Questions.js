// import React, { useState } from "react";
//import React from "react";
import React, { useState, useEffect } from "react";
import axios from "axios";
import {QnAcontainer,Qcontainer} from "../styles/QuestionsStyle";
import Vote from "../components/Vote";

//<Link to = {"/"}> Home </Link>
function Questions(){
  const [posts, setPosts] = useState([]);
  const [loading, setLoading] = useState(false);
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

    return (
        <>
          <QnAcontainer posts={(posts)} loading={loading}>
            <p>title</p>{/* 게시글 추가 */}
            <Qcontainer>
            <Vote />
            <div>
            <div className="Qbody"></div>
            <div className="Qfooter">
              <span>Edit</span>
              <p>user</p>
              </div>
            <div className="Qcomment">
              <span>Add a comment</span>
            </div>
            </div>
            </Qcontainer>

           </QnAcontainer>
        </>
      );
    }
    
    export default Questions;
    