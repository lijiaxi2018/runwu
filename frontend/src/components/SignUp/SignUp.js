import React, { useState, useReducer, useEffect } from 'react';
import Axios from 'axios';
import './SignUp.css';

const formReducer = (state, event) => {
  if (event.reset) {
    return {
      username: "",
      password: "",
      confirm: "",
      organization: ""
    }
  } else {
    return {
      ...state,
      [event.name]: event.value
    }
  }
}

function SignUp() {
  // API[POST]: SignUp 
  const clientSignUp = async (formData) => {
    const response = await Axios.post(`http://localhost:8081/api/Account/SignUp`, {
      username: formData.username,
      password: formData.password,
      organization: formData.organization,
      avatar: ""
    })
    console.log(response)
    console.log(response.data)

    handleSubmitted(response.data)
  };

  const [submitting, setSubmitting] = useState(false);
  const [submitted, setSubmitted] = useState(false);
  const [submittedMessage, setSubmittedMessage] = useState("");
  const [formData, setFormData] = useReducer(formReducer, {
    username: "",
    password: "",
    confirm: "",
    organization: ""
  });

  const [matching, setMatching] = useState(true);
  useEffect(() => {
    setMatching(formData.password === formData.confirm)
  }, [formData.password, formData.confirm]);

  const [filled, setFilled] = useState(false);
  useEffect(() => {
    setFilled(formData.username.length > 0 && formData.password.length > 0 && formData.confirm.length > 0)
  }, [formData.username, formData.password, formData.confirm]);

  const handleChange = event => {
    setFormData({
      name: event.target.name,
      value: event.target.value
    });
  }
  
  const handleSubmit = event => {
    event.preventDefault();
    if (matching && filled) {
      setSubmitting(true);

      clientSignUp(formData);

      setSubmitting(false);
      setFormData({
        reset: true
      })
    }
  }

  const handleSubmitted = code => {
    if (code === 200) {
      setSubmittedMessage("Successfully submitted!")
    } else if (code === 201) {
      setSubmittedMessage("Username already existed!")
    } else {
      setSubmittedMessage("Please check you internet!")
    }

    setSubmitted(true)
    setFilled(true);

    setTimeout(() => {
      setSubmitted(false);
      setFilled(false);
    }, 3000);
  }

  return (
    <div className="wrapper">
      <h1>Welcome</h1>

      {submitting &&
        <div>
          <h2>You are submitting...</h2>
          <p>Username: {formData.username}</p>
          <p>Password: {formData.password}</p>
          <p>Organization: {formData.organization}</p>
        </div>
      }

      {(!filled) &&
        <div>
          <p style={{ color: 'red' }}>Please fill all the required fields.</p>
        </div>
      }

      {(!matching) &&
        <div>
          <p style={{ color: 'red' }}>Passwords do not match. Please re-enter the passwords.</p>
        </div>
      }

      {(submitted) &&
        <div>
          <p style={{ color: 'blue' }}>{submittedMessage}</p>
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

          <label>
            <p>Confirm Password*</p>
            <input name="confirm" onChange={handleChange} value={formData.confirm}/>
          </label>

          <label>
            <p>Organization</p>
            <input name="organization" onChange={handleChange} value={formData.organization}/>
          </label>
        </fieldset>
        <button type="submit" disabled={submitting}>Sign Up</button>
      </form>
    </div>
  )
}

export default SignUp;