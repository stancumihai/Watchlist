import React, { useState, useEffect } from "react";
import Form from "react-bootstrap/Form";
import "./Login.css";
import { useHistory} from "react-router";
import get_users from '../../../actions/users/userActions.js'
import { connect } from 'react-redux'

const Login = props => {
  
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  
  const history = useHistory()

  useEffect(() =>{
    props.get_users()
    // eslint-disable-next-line react-hooks/exhaustive-deps
  },[])

  function handleSubmit(event) {
    event.preventDefault()
    const user = {
      user_name : username,
      password: password
    }

    for (var counter in props.users){
      if(props.users[counter].user_name === user.user_name && props.users[counter].password === user.password){
            history.push('/profile' , { id : parseInt(counter + 1)}) 
          return;         
      }
    }
    alert('User does not exist, please register')
    window.location.reload()
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

const mapStateToProps = state =>({
  users:state.auth.items
})

export default  connect(mapStateToProps, {get_users})(Login)