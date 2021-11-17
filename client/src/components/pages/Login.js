import React, { useState } from "react";
import Form from "react-bootstrap/Form";
import UserService from "../../services/UserService.js";
import "./Login.css";
import { useHistory} from "react-router";

export default function Login() {
  
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  
  const history = useHistory()

  function handleSubmit(event) {
    event.preventDefault()
    const user = {
      user_name : username,
      password: password
    }
    
    const users = UserService.getUsers()

    users.then(res => {
      for (var counter in res.data){
        if(res.data[counter].user_name === user.user_name && res.data[counter].password === user.password){
              history.push('/profile' , { id : parseInt(counter + 1)}) 
            return;         
        }
      }
      alert('User does not exist, please register')
      window.location.reload()
    })
  }

  return (
    <div className="Login">
      <div className="background">
        <div className="shape"></div>
        <div className="shape"></div>
     </div>
      <form onSubmit={handleSubmit}>
        <h3>Login</h3>
        <Form.Group size="lg" controlId="username">
          <Form.Label>User Name</Form.Label>
          <Form.Control
            autoFocus
            type="text"
            value={username}
            onChange={(e) => {
              setUsername(e.target.value)
            }}
          />
        </Form.Group>
        <Form.Group size="lg" controlId="password">
          <Form.Label >Password</Form.Label>
          <Form.Control
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </Form.Group>
       <input 
            type = "submit"
            value = "Login"
            className = "btn btn-dark btn-block"
          />
      </form>
    </div>
  );
}