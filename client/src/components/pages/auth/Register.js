import React, { useState} from "react";
import Form from "react-bootstrap/Form";
import LoginService from "../../../services/LoginService.js";
import { Redirect } from "react-router";
import "./Login.css";

export default function Login() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [name, setName] = useState("");
  const [surname, setSurname] = useState("");
  const [email, setEmail] = useState("");
  const [redirect, setRedirect] = useState (false)

  function handleSubmit(event) {
    event.preventDefault()
    
    const enteredUser = {
      user_name : username,
      password,
      name,
      surname,
      email
    }
    
    LoginService.createUser(enteredUser)
    .then(() => {
      setTimeout(() =>{
        alert(enteredUser.user_name +  " has registered")
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
        <div className="shape-register"></div>
     </div>
      <form className = "registerForm" onSubmit={handleSubmit}>
        <h3>Register</h3>
        <Form.Group size="lg">
          <Form.Label className = "required">User Name</Form.Label>
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
          <Form.Label className = "required">Password</Form.Label>
          <Form.Control
            id = "register-password"
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </Form.Group>
        <Form.Group size="lg">
          <Form.Label >User</Form.Label>
          <Form.Control
            id = "register-name"
            type="text"
            value={name}
            onChange={(e) => setName(e.target.value)}
          />
        </Form.Group>
        <Form.Group size="lg">
          <Form.Label >Surname</Form.Label>
          <Form.Control
            id = "register-surname"
            type="text"
            value={surname}
            onChange={(e) => setSurname(e.target.value)}
          />
        </Form.Group>
        <Form.Group size="lg">
          <Form.Label >Email</Form.Label>
          <Form.Control
            id = "register-email"
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
        </Form.Group>
       <input 
            style = {{background:"cornflowerblue"}}
            type = "submit"
            value = "Register"
            className = "btn btn-dark btn-block"
          />
      </form>
    </div>
  );
}