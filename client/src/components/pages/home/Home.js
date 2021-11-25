import React, { useEffect, useState } from "react";
import Rating from '@material-ui/lab/Rating';
import {Button , CardContent , Card,Typography,CardMedia } from '@material-ui/core';
import { Link } from "react-router-dom";
import {useLocation } from "react-router-dom";
import {useHistory} from "react-router";
import UserService from "../../../services/UserService.js";
import MovieService from "../../../services/MovieService.js";
import "../movie/MovieCard.css"
import { alpha, makeStyles } from '@material-ui/core/styles';

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
    width: "100%",
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
  },
  inputRoot: {
    color: 'inherit',
  },
  inputInput: {
    padding: theme.spacing(1, 1, 1, 0),
    paddingLeft: `calc(1em + ${theme.spacing(4)}px)`,
    transition: theme.transitions.create('width'),
    width: '100%',
    [theme.breakpoints.up('sm')]: {
      width: '20ch',
      '&:focus': {
        width: '30ch',
      },
    },
  },
  search: {
    position: 'relative',
    borderRadius: 20,
    backgroundColor: alpha(theme.palette.common.white, 0.15),
    '&:hover': {
      backgroundColor: alpha(theme.palette.common.white, 0.25),
    },
    marginLeft: 0,
    width: '100%',
    [theme.breakpoints.up('sm')]: {
      marginLeft: theme.spacing(1),
      width: 'auto',
    },
  },
  searchIcon: {
    padding: theme.spacing(0, 2),
    height: '100%',
    position: 'absolute',
    pointerEvents: 'none',
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
  },
}));

export default function Home() {
  const classes = useStyles();
  const location = useLocation();     
  const history = useHistory()

  const [movies, setMovies] = useState(null);
  const [averageRating, setAverageRating] = useState(0.0)
  const user = location.state;
  // eslint-disable-next-line no-unused-vars
  const [value, setValue] = useState(0);

  useEffect(() => {
    MovieService.getMovies().then(resp =>  setMovies(resp.data))
    // eslint-disable-next-line react-hooks/exhaustive-deps
  },[])
  
  const load_average_rating = (id) =>{
    MovieService.getReviewsByMovie(id).then(res => {
      let sum = 0;
      if(res.data.length > 0){
        for(var counter in res.data){
          sum += res.data[counter].rating
        }
        setAverageRating(sum/res.data.length)
      }else{
        setAverageRating(0)
      }
    })
    return averageRating
  }

  const move_profile = () =>{
    history.push('/profile' , { id : parseInt(user.user.id)})
  } 
  
  const add_to_watchlist = (userId , movieId) =>{
    UserService.addMovie(userId, movieId) 
    MovieService.getMovieById(movieId)
          .then(resp => alert(resp.data.name + " was added to watchlist"))
          .catch(" Movie already wa added to watchlist")
  }

  return (
    (movies !== null) && 
    <div className={classes.container} style = {{background : "#333333"}}>
    <Button style = {{color:"white", backgroundColor : "cornflowerblue"}} onClick = {move_profile}> Move Back to Profile</Button>
    {movies.map(movie =>
      <Card id =  "movie-card" className={classes.card} key={movie.id}>
          <CardMedia className={classes.media} image = {movie.url} title={ movie.name } />
          <CardContent>
            <Link to={ "/movie/" + movie.id } color="inherit" style={{textDecoration: "none"}}>
            <Typography gutterBottom variant="h5" component="h2">
            {movie.name}
            </Typography>
            </Link>
            <Rating name = "rating" readOnly value = {() => load_average_rating(movie.id)}/>
            <Button onClick = {() => add_to_watchlist(user.user.id , movie.id)} style = {{color : "red"}}>Add To WatchList</Button>
          </CardContent>
      </Card> 
    )}
   </div>
  );
}
