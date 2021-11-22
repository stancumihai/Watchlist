/* eslint-disable no-unused-vars */
import React, { useEffect, useState } from "react";
import { makeStyles } from '@material-ui/core/styles';
import {Typography , Box , CircularProgress, Link , List} from '@material-ui/core';
import MovieService from "../../../services/MovieService.js";

const useStyles = makeStyles((theme) => ({
  title: {
  	color: "white",
  	paddingTop: 10
  },
  date: {
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
    width: 250,
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
  link: {
    color: "white",
    padding: 10
  },
  list: {
    display: 'inline-block'
  },
  people: {
    width: 150,
    height:200,
    margin: 15,
    [theme.breakpoints.down('xs')]: {
      maxWidth: 60
    }
  }
}));

export default function Movie() {
  
  const classes = useStyles();

  const [movieId,setMovieId] = useState(0)
  const [actors , setActors] = useState([])
  const [moviePoster , setMoviePoster] = useState(null)

  useEffect(() => {
    graspMovieId()
  // eslint-disable-next-line react-hooks/exhaustive-deps
  },[])

  const graspMovieId = () =>{
    let id = '';
    for ( var char in window.location.href){
      if(char >= 28 && char !== undefined ){
        id += window.location.href[char];
      }
    }
    setMovieId(parseInt(id))
    MovieService.getActorsByMovie(id).then(res => setActors(res.data))
    MovieService.getMovieById(id).then(res => setMoviePoster(res.data))
  }

  return (
    (actors != null & moviePoster !== null) && 
  	<div style = {{background:'black'}}>
    <CircularProgress style={{ display: moviePoster?"none":"block", margin: "20px auto" }} />
  	<Box display="flex" className={classes.box} justifyContent="flex-start" m={1} p={1}>
    	<Box p={1}>
          <img className={classes.poster} src={ moviePoster.url } alt ='loading' />
        </Box>
        <Box p={1}>
          <Typography variant="h3" gutterBottom className={classes.title}>{ moviePoster.name}</Typography>
          <Typography variant="subtitle1" gutterBottom className={classes.plot}>{ moviePoster.description}</Typography>
          <Typography variant="h5" gutterBottom className={classes.title}>Cast:</Typography>
          <Typography variant="subtitle1" gutterBottom className={classes.title}>
            { actors.map(actor=><Link href={"/actor/" + actor.id} className={classes.link}>{actor.name} {actor.surname}</Link>)}</Typography>
        </Box>
  	</Box>
    <Box display="flex" className={classes.box} justifyContent="flex-start" m={1} p={1}>
      <Box p={1}>
        <Typography variant="h4" gutterBottom className={classes.title}>Cast:</Typography>
        <List className={classes.list}>
          {actors.map(actor=>(<Link href={"/actor/"+actor.id}><img className={classes.people} src={actor.url} alt ='Loading' /></Link>))}
        </List>
      </Box>
    </Box>
  	
    </div>
  )
}