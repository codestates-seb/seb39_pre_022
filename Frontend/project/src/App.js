import Navbar from "./components/Navbar";
//import Questions from "./components/Questions" // 목록리스트
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import './App.css';

function App() {
  return (
    <Router>
    <Navbar />
    <Routes>
      <Route path="/" />
      <Route path="/search" />
      <Route path="/login" />
      <Route path="/singup" />
    </Routes>
  </Router>
  );
}

export default App;
