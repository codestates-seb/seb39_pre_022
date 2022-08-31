import Navbar from "./components/Navbar";
import Posts from "./components/Posts";
import Vote from "./components/Vote";
//import PostList from "./components/PostList";
//import PostView from "./components/PostView";

//import Questions from "./components/Questions" // 목록리스트
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import './App.css';

function App() {
  return (
    <>
    <Router>
    <Navbar />
    <Routes>
      <Route path="/" />
      <Route path="/search" />
      <Route path="/login" />
      <Route path="/singup" />     
      <Route path="/questoins" />     
    </Routes>
  </Router>
   <Posts />
<Vote />
   </>  
   /* <Route path='/postView'  ><PostView/></Route>
        <Route path='/PostList'  ><PostList/></Route> */
   );
}

export default App;
