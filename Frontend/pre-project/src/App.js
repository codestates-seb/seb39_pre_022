import './App.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Main from './component/Main';
import Login from './component/LogIn';
import SignUp from './component/SignUp';
import Question from './component/Question';
import AskQuestion from './component/AskQuestion';
import Pege from "./component/page/Posts";

export default function App() {
  return (
    <BrowserRouter>
      <div className="App">
        <Routes>
          <Route path='/' element={<Main />} />
          <Route path='/questions' element={<Pege />} />
          <Route path='/question' element={<Question />} />
          <Route path='/askquestion' element={<AskQuestion />} />
          <Route path='/login' element={<Login />} />
          <Route path='/signup' element={<SignUp />} />
        </Routes>
      </div >
    </BrowserRouter>
  );
}

// 1rem === 12-3px
// 세밀하게 작업할 때는 .단위