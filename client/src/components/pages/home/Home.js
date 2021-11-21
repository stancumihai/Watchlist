import React, { useEffect, useState } from "react";
import Rating from '@material-ui/lab/Rating';
import {Link , Button , CardContent , Card,Typography,CircularProgress, CardMedia , makeStyles} from '@material-ui/core';
import {useLocation } from "react-router-dom";
import {useHistory} from "react-router";
import UserService from "../../../services/UserService.js";
import MovieService from "../../../services/MovieService.js";
import "../movie/MovieCard.css"


const useStyles = makeStyles((theme) => ({
  
  card: {
    width: "250px",   
    height : "55  0px",
    margin: 20,
    display: "inline-block",
    background: "rgb(30,30,30)",
    color: "white",
    position : 'relative'
  },
  container: {
    width: "90%",
    margin: "auto"
  },
  media: {
    height: 375,
    filter: "brightness(0.7)"
  },
  plot: {
    letterSpacing: 2,
    color: "lightgrey",
    marginTop: 10,
    marginBottom: 15
  },
  button:{
    color:"cornflowerblue",
    position : "absolute",  
    bottom : 0
  }
}));

export default function Home() {
  const classes = useStyles();
  const location = useLocation();     
  const history = useHistory()

  const [movies, setMovies] = useState(null);
  const user = location.state;

  useEffect(() => {
    MovieService.getMovies().then(resp =>  setMovies(resp.data))
    // eslint-disable-next-line react-hooks/exhaustive-deps
  },[])
  
  function move_profile (){
    history.push('/profile' , { id : parseInt(user.user.id)}) 
  } 
  
  function add_to_watchlist(userId , movieId){
    UserService.addMovie(userId, movieId) 
    MovieService.getMovieById(movieId).then(resp => alert(resp.data.name + " was added to watchlist"))
  }

  return (
   <div className={classes.container}>
   <Button style = {{color:"white", backgroundColor : "cornflowerblue"}} onClick = {move_profile}> Move Back to Profile</Button>
   <CircularProgress style={{ display: movies?"none":"block", margin: "20px auto" }} />
   {movies?movies.map(movie =>
    <Card id =  "movie-card" className={classes.card} key={movie.id}>
        <CardMedia className={classes.media} image = {movie.url} title={ movie.name } />
        <CardContent>
          <Link href={ "/movie/" + movie.id } color="inherit" style={{textDecoration: "none"}}>
          <Typography gutterBottom variant="h5" component="h2">
          {movie.name}
          </Typography>
          </Link>
          <Rating readOnly value={ Math.floor(Math.random() * 5 + 1)}/>
          <Button onClick = {() => add_to_watchlist(user.user.id , movie.id)} style = {{color : "red"}}>Add To WatchList</Button>
        </CardContent>
    </Card> 
   ):""}
   </div>
  );
}
