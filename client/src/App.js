import React from 'react'
import { BrowserRouter as Router, Switch, Route } from "react-router-dom"
import Login from './components/pages/auth/Login.js'
import Register from './components/pages/auth/Register.js'
import Profile from './components/pages/profile/Profile.js'
import Home from './components/pages/home/Home.js';
import Navbar from './components/layout/Navbar.js';
import Movie from './components/pages/movie/Movie.js';
import Actor from './components/pages/actor/Actor.js';
import { makeStyles } from '@material-ui/core/styles';
import './App.css'
import store from './redux/store.js'
import {Provider} from 'react-redux'

const useStyles = makeStyles((theme) => ({
  notFound: {
    position: "absolute",
    left: "50%",
    top: "50%",
    transform: "translate(-50%,-50%)"
  }
}));

function App() {

  const classes = useStyles();

  return (
    <Provider store = {store}>
      <Router>
         <div className="App">
          <Navbar title = "App Critic" icon = "fab fa-github"/>
           <Switch>
             <Route exact path = '/' component = {Login}/>
             <Route exact path = '/register' component = {Register}/>
             <Route exact path = '/home' component = {Home}/>
             <Route exact path = '/profile' component = {Profile}/>
             <Route path="/movie/:query"><Movie/></Route>
             <Route path="/actor/:query"><Actor/></Route>
             <Route path="*">
              <img src="/404.gif" className={classes.notFound} alt = "Loading ..."/>
            </Route>
           </Switch>
         </div>
      </Router>
    </Provider>
  );
}

export default App;
