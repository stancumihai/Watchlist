/* eslint-disable react-hooks/exhaustive-deps */
/* eslint-disable jsx-a11y/anchor-is-valid */
import React , {useState, useEffect} from "react";
import { useLocation } from "react-router-dom";
import {Button , Typography, CardContent, Card,CardMedia, makeStyles} from '@material-ui/core';
import { Link } from "react-router-dom";
import {useHistory , withRouter} from "react-router";
import UserService from "../../../services/UserService.js"
import "./Profile.css"

export function Profile() {
    
    const location = useLocation();     
    let myParam = parseInt(location.state.id[0]) + 1;
    const [user,setUser] = useState(null)
    const [movies, setMovies] = useState ([])

    const history = useHistory()

    const useStyles = makeStyles((theme) => ({
        card: {
            left:400,
            height :10,
            margin: 20,
            display: "inline-block",
            background: "rgb(30,30,30)",
            color: "white",
            position : 'relative'
          },
          moveList:{
            width: 1000
          },
          media: {
            height: 300,
            filter: "brightness(0.7)"
          },
          movieTitle: {
            textAlign: "right",
          }
        }));


    const classes = useStyles();

    const loadUserById = () => {
        UserService.getUserById(myParam).then(res =>{
            setUser(res.data);
        });
    }
    
    const loadUserMovies = () =>{
        UserService.getUsersMovie(myParam).then(res =>{
            setMovies(res.data);
        })
    }

    useEffect(() =>{

        if(localStorage.getItem('state') === null){
            history.push('/')
        }

        if(typeof location.state.id == "number"){
            myParam = location.state.id;
        }else{
            myParam = parseInt(location.state.id.substring(0,location.state.id.length - 1)) + 1
        }

        loadUserById();
        loadUserMovies();
     // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [])

    const logOut = () => {
        // eslint-disable-next-line no-restricted-globals
        var response = confirm("Are you sure you want to logotout ?")
        if(response === true){
            localStorage.clear();
            history.push('/' , ) 
        }
    }   

    const  move_profile = () =>{
        history.push('/home' , {user}) 
    }

    const move_to_cinema_list = () =>{
        history.push('/cinema');
    }

    const move_to_my_reviews = () =>{
        history.push('/userReview')
    }

    return(
    (user != null & movies.length !== null) && 
    <div className="wrapper" style = {{background : "#333333"}}>
        <div className="sidebar">
            <div className="profile-nav col-md-3">
                <div className="panel">
                    <div className="user-heading round">
                        <a>
                            <img src="https://bootdey.com/img/Content/avatar/avatar3.png" alt=""/>
                        </a>
                        <h1>{user.user_name} {user.surname}</h1>
                        <p>{user.email}</p>
                    </div>
                </div>
            </div>
            <div className="content">
                <ul>
                    <li>
                        <a>
                            <i className="fas fa-home">
                                <Button onClick = {move_profile} style = {{fontSize : "10px" ,  display : "inline"}}>Move to Movies</Button>
                            </i>
                        </a>
                    </li>
                    <li>
                        <a>
                            <i className="fas fa-user-friends">
                                <Button onClick = {move_to_my_reviews} style = {{fontSize : "10px" ,  display : "inline"}}>
                                My Reviews</Button>
                            </i>
                        </a>
                    </li>
                    <li>
                        <a>
                            <i className="fas fa-tachometer-alt">
                                <Button onClick = {move_to_cinema_list} style = {{fontSize : "10px" ,  display : "inline"}}>Cinema List</Button>
                            </i>
                        </a>
                    </li>
                   
                    <li>
                        <a>
                            <i className="fas fa-tachometer-alt">
                                <Button onClick = {logOut} style = {{fontSize : "10px" ,  display : "inline"}}>Log Out</Button>
                            </i>
                        </a>
                    </li>
            </ul>
            </div>
        </div>
        <div id = 'movieList' className={classes.moveList}>
        {movies.map(movie =>
                        <Card id =  "movie-card" className={classes.card} key={movie.id}>
                            <CardMedia className={classes.media} image = {movie.url} title={ movie.name } />
                            <CardContent>
                            <Link to={ "/movie/" + movie.id } color="inherit" style={{textDecoration: "none"}}>
                                <Typography className={classes.movieTitle} gutterBottom variant="h5" component="h2">
                                    {movie.name } {movie.year}
                                </Typography>
                            </Link>
                            </CardContent>
                        </Card> 
                    )}
        </div>
    </div>
)}

export default withRouter(Profile)