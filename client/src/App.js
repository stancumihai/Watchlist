import React from 'react'
import './components/pages/Login.css'
import Login from "./components/pages/Login.js"
import Register from './components/pages/Register.js';
import Profile from './components/pages/Profile.js'
import Navbar from './components/layout/Navbar.js';
import Users from './components/db_tables/Users.js'
import Actors from './components/db_tables/Actors.js'
import Movies from './components/db_tables/Movies.js'
import Home from './components/pages/Home.js';
import './App.css'
import { BrowserRouter as Router, Switch, Route } from "react-router-dom"

function App() {
  return (
      <Router>
         <div className="App">
          <Navbar title = "App Critic" icon = "fab fa-github"/>
           <Switch>
             <Route exact path = '/' component = {Login}/>
             <Route exact path = '/register' component = {Register}/>
             <Route exact path = '/home' component = {Home}/>
             <Route exact path = '/users' component = {Users}/>
             <Route exact path = '/movies' component = {Movies}/>
             <Route exact path = '/actors' component = {Actors}/>
             <Route exact path = '/profile' component = {Profile}/>
           </Switch>
         </div>
      </Router>
  );
}

export default App;
