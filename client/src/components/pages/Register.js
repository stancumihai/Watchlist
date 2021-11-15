import React, { useState} from "react";
import Form from "react-bootstrap/Form";
import "./Login.css";
import LoginService from "../../services/LoginService.js";
import { Redirect } from "react-router";

export default function Login() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [redirect, setRedirect] = useState (false)

  function handleSubmit(event) {
    event.preventDefault()
    const user = {
      user_name : username,
      password: password
    }
    LoginService.createUser(user)
    .then(() => {
      setTimeout(() =>{
        alert(user.user_name +  " has registered")
        setRedirect(true)
      }, 1000)
    })
    .catch(err => {
      if(err.response){
        if(err.response.status === 404){
          alert('Error page not found')
        }else{
          alert('Choose different user name')
          window.location.reload()
        }
      }
    })
  }

  return (
    redirect 
    ? <Redirect to = '/'/>
    :
    <div className="Login">
      <div className="background">
        <div className="shape"></div>
        <div className="shape"></div>
     </div>
      <form onSubmit={handleSubmit}>
        <h3>Register</h3>
        <Form.Group size="lg">
          <Form.Label >User Name</Form.Label>
          <Form.Control
            id = "register-username"
            autoFocus
            type="text"
            value={username}
            onChange={(e) => {
              setUsername(e.target.value)
            }}
          />
        </Form.Group>
        <Form.Group size="lg">
          <Form.Label >Password</Form.Label>
          <Form.Control
            id = "register-password"
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </Form.Group>
       <input 
            type = "submit"
            value = "Register"
            className = "btn btn-dark btn-block"
          />
      </form>
    </div>
  );
}