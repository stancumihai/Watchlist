import React, { useEffect, useState } from "react";
import { makeStyles } from '@material-ui/core/styles';
import {Box,Typography ,List} from '@material-ui/core';
import { Link } from "react-router-dom";
import ActorService from "../../../services/ActorService.js";

const useStyles = makeStyles((theme) => ({
  title: {
  	color: "white",
  	paddingTop: 10
  },
  prof: {
  	color: "lightgrey"
  },
  plot: {
  	color: "white",
  	paddingTop: 10
  },
  button: {
  	margin: 10,
  	fontWeight: "bolder"
  },
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
  list: {
    display: 'inline-block'
  },
  movies: {
    maxWidth: 150,
    margin: 15,
    [theme.breakpoints.down('xs')]: {
      maxWidth: 60
    }
  }
}));


export default function Actor() {
  const [actor, setActor] = useState(0);
  const [actorId,setActorId] = useState(0)
  const [actorMovies, setActorMovies]  = useState([])
  const classes = useStyles();
  
  useEffect(() => {
    graspActorId()
    // eslint-disable-next-line react-hooks/exhaustive-deps
  },[])
  
  const graspActorId = () =>{
    let id = '';
    for ( var char in window.location.href){
      if(char >= 28 && char !== undefined ){
        id += window.location.href[char];
      }
    }
    setActorId(parseInt(id))
    ActorService.getActorById(id).then(res => setActor(res.data))
    ActorService.getMoviesByActor(id).then(res => setActorMovies(res.data))
  }

  return (
    (actor != null & actorId != null && actorMovies!=null) && 
  	<div  style = {{background : "#333333"}}>
  	<Box display="flex" className={classes.box} justifyContent="flex-start">
    	  <Box p={1}>
          <img className={classes.poster} src = {actor.url} alt= 'Loading'/>
        </Box>
        <Box p={1}>
          <Typography variant="h3" gutterBottom className={classes.title}> {actor.name } {actor.surname}</Typography>
          {/* <Typography variant="h6" gutterBottom className={classes.prof}>{ actor.profession}</Typography> */}
        </Box>
  	</Box>
    <Box display="flex" className={classes.box} justifyContent="flex-start" m={1} p={1}>
      <Box p={1}>
        <Typography variant="h4" gutterBottom className={classes.title}>Movies:</Typography>
        <List className={classes.list}>
          {actorMovies.map(movie=>(<Link key={movie.id} to={"/movie/"+movie.id}>
            <img className={classes.movies} src={movie.url} alt = 'loading'/></Link>))}
        </List>
      </Box>
    </Box>
    </div>
  )
}