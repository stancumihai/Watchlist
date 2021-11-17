/* eslint-disable jsx-a11y/anchor-is-valid */
import React , {useState, useEffect} from "react";
import { useLocation } from "react-router-dom";
import { makeStyles } from '@material-ui/core/styles';
import UserService from "../../services/UserService.js"
import Box from '@material-ui/core/Box';
import Button from '@material-ui/core/Button';
import CircularProgress from '@material-ui/core/CircularProgress';
import {useHistory} from "react-router";

import "./Profile.css"

export default function Profile() {
    
    const location = useLocation();     
    const myParam = parseInt(location.state.id);
    const [user,setUser] = useState(null)
    const [profile, setProfile] = useState ([])
    
    const history = useHistory()

    const useStyles = makeStyles((theme) => ({
        poster: {
          maxWidth: 250,
          margin: 20,
          display: "inline-block",
          [theme.breakpoints.down('xs')]: {
            maxWidth: "80%"
          }
        },
         box: {
          [theme.breakpoints.down('xs')]: {
            flexWrap: "wrap"
          }
        },
        text: {
            color: "white",
            paddingTop: 10
        },
        btn: {
            margin: 25,
            fontWeight: "bolder"
        },
        avatar: {
          margin: 20,
          height: 200,
          width: 200
        }
      }));

    function loadUserById(){
        UserService.getUserById(myParam).then(res =>{
            setUser(res.data);
        });
    }
    
    function loadUserMovies(){
        UserService.getUsersMovie(myParam).then(res =>{
            setProfile(res.data);
        })
    }

    useEffect(() =>{
        console.log(location.state.id);
        loadUserById();
        loadUserMovies();
     // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [])

    function logOut() {
        window.localStorage.clear();
        window.location.href="/";
    }   

    const classes = useStyles();

    function move_profile (){
        history.push('/home' , {user}) 
      }

    return(
        (user != null & profile.length !== null) && 
        <div>
        <button onClick = {move_profile}> Go to Movies</button>
        <CircularProgress style={{ display: profile?"none":"block", margin: "20px auto"}}/>   
        <div className="container bootstrap snippets bootdey">
            <div className="row">
            <div className="profile-nav col-md-3">
                <div className="panel">
                    <div className="user-heading round">
                        <a>
                            <img src="https://bootdey.com/img/Content/avatar/avatar3.png" alt=""/>
                        </a>
                        <h1>{user.user_name}</h1>
                        <p>{user.email}</p>
                    </div>
                </div>
            </div>
            
            <div className="profile-info col-md-9">
                <div className="panel">
                <div className="bio-graph-heading">
                    <h1>Hello {user.user_name}</h1>
                </div>
                <div className="panel-body bio-graph-info">
                  <h1>Details</h1>
                <div className="row">
                    <div className="bio-row">
                        <p><span>Name </span>: {user.name}</p>
                    </div>
                    <div className="bio-row">
                        <p><span>SurName </span>: {user.surname}</p>
                    </div>
                    <div className="bio-row">
                        <p><span>Email </span>: {user.email}</p>
                    </div>
                    <div className = "movie-list">
                    {   
                        profile.map((movie) => (
                            <div>
                            <ul>
                                <li>{movie.name}</li>
                                <ul>
                                    <li>{movie.year}</li>
                                    <li>{movie.genre}</li>
                                    <li>{movie.director}</li>
                                    <li>{movie.language}</li>
                                </ul>
                            </ul>
                        </div>
                        ))
                    }
                    </div>
                    </div>
                    </div>
                    <Box display="flex" className={classes.box} justifyContent="flex-start" m={1} p={1}>
                        <Box p={1}>
                            <Button variant="contained" onClick={logOut} className={classes.btn}>Log Out</Button>
                        </Box>
                    </Box>
                    </div>
                     <div>
                    </div>
                    </div>
                </div>
            </div>
        </div>
)}

