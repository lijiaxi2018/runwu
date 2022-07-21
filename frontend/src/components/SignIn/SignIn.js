import React, { useState, useReducer, useEffect } from 'react';
import Axios from 'axios';
import './SignIn.css';

// API[POST]: SignUp 
// http://localhost:8081/api/Account/SignIn?username={username}&password={password}
const clientSignIn = async (formData) => {
  const response = await Axios.get(`http://localhost:8081/api/Account/SignIn`, {
    params: {
      username: formData.username,
      password: formData.password
    }
  })
  console.log(response)
  console.log(response.data)
};

const formReducer = (state, event) => {
  if (event.reset) {
    return {
      username: "",
      password: "",
    }
  } else {
    return {
      ...state,
      [event.name]: event.value
    }
  }
}

function SignIn() {
  const [submitting, setSubmitting] = useState(false);
  const [formData, setFormData] = useReducer(formReducer, {
    username: "",
    password: "",
  });

  const [filled, setFilled] = useState(false);
  useEffect(() => {
    setFilled(formData.username.length > 0 && formData.password.length > 0)
  }, [formData.username, formData.password]);

  const handleChange = event => {
    setFormData({
      name: event.target.name,
      value: event.target.value
    });
  }
  
  const handleSubmit = event => {
    event.preventDefault();
    if (filled) {
      setSubmitting(true);

      clientSignIn(formData);

      setSubmitting(false);
      setFormData({
        reset: true
      })
    }
  }

  return (
    <div className="wrapper">
      <h1>Welcome Back</h1>

      {submitting &&
        <div>
          <h2>You are submitting...</h2>
          <p>Username: {formData.username}</p>
          <p>Password: {formData.password}</p>
        </div>
      }

      {(!filled) &&
        <div>
          <p style={{ color: 'red' }}>Please fill all the required fields.</p>
        </div>
      }

      <form onSubmit={handleSubmit}>
        <fieldset disabled={submitting}>
          <label>
            <p>Username*</p>
            <input name="username" onChange={handleChange} value={formData.username}/>
          </label>

          <label>
            <p>Password*</p>
            <input name="password" onChange={handleChange} value={formData.password}/>
          </label>
        </fieldset>
        <button type="submit" disabled={submitting}>Sign In</button>
      </form>
    </div>
  )
}

export default SignIn;