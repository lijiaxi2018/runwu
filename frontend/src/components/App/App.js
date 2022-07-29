import React from 'react';
import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom'
import './App.css';
import SignUp from '../SignUp/SignUp';
import SignIn from '../SignIn/SignIn';
import UploadImage from '../UploadImage/UploadImage';

function App() {
  return (
    <div className="wrapper">
      <h2>Welcome to Runwu, an accessible platform for sharing art works and ideas.</h2>
      <Router>
        <nav>
          <ul>
            <li><Link to="/signup">Sign Up</Link></li>
            <li><Link to="/signin">Sign Up</Link></li>
            <li><Link to="/upload">Upload</Link></li>
          </ul>
        </nav>
        <Routes>
          <Route path="/" element={<></>}/>
          <Route path="/signup" element={<SignUp/>}/>
          <Route path="/signin" element={<SignIn/>}/>
          <Route path="/upload" element={<UploadImage/>}/>
        </Routes>
      </Router>
    </div>
  )
}

export default App;